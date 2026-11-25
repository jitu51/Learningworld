package com.example.data.repository

import com.example.data.content.ContentRepository
import com.example.data.local.*
import com.example.data.model.LearningActivity
import com.example.data.model.WorldId
import com.example.engine.SmartLearningEngine
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

class LearningRepository(
    private val dao: LearningDao,
    private val prefsRepo: ParentPreferencesRepository
) {
    val worldProgressFlow: Flow<List<WorldProgressEntity>> = dao.observeWorldProgress()
    val recentAttemptsFlow: Flow<List<QuestionAttemptEntity>> = dao.observeRecentAttempts()
    val dailyLogsFlow: Flow<List<DailyLogEntity>> = dao.observeDailyLogs()
    val achievementsFlow: Flow<List<AchievementEntity>> = dao.observeAchievements()
    val unlockedCharactersFlow: Flow<List<CharacterUnlockEntity>> = dao.observeUnlockedCharacters()
    val preferencesFlow: Flow<ParentPreferences> = prefsRepo.preferencesFlow

    suspend fun ensureStarterCharacterUnlocked() {
        dao.insertUnlockedCharacter(CharacterUnlockEntity("owl_hoot"))
    }

    suspend fun recordActivityOutcome(
        activity: LearningActivity,
        isCorrect: Boolean,
        isDailyChallenge: Boolean
    ) {
        val now = System.currentTimeMillis()
        val today = SmartLearningEngine.todayDateKey()

        dao.insertAttempt(
            QuestionAttemptEntity(
                activityId = activity.id,
                worldId = activity.worldId.name,
                topicTag = activity.topicTag,
                isCorrect = isCorrect,
                isVocabulary = activity.isVocabulary,
                timestampMillis = now
            )
        )

        val currentWorld = dao.getWorldProgress(activity.worldId.name)
            ?: WorldProgressEntity(worldId = activity.worldId.name)

        val worldTotal = ContentRepository.activitiesForWorld(activity.worldId).size.coerceAtLeast(1)
        val newCompleted = if (isCorrect) currentWorld.completedActivities + 1 else currentWorld.completedActivities
        var earnedPoints = if (isCorrect) 10 else 2
        if (isCorrect && newCompleted % 3 == 0) earnedPoints += 20 // Lesson completion bonus
        if (isCorrect && newCompleted == worldTotal) earnedPoints += 100 // World completion bonus
        if (isCorrect && isDailyChallenge) earnedPoints += 25 // Daily challenge bonus

        val newStars = if (isCorrect) currentWorld.stars + 1 else currentWorld.stars
        val masteredSet = currentWorld.masteredTopicsCsv
            .split(",")
            .filter { it.isNotBlank() }
            .toMutableSet()
        if (isCorrect) masteredSet.add(activity.topicTag)

        dao.upsertWorldProgress(
            currentWorld.copy(
                points = currentWorld.points + earnedPoints,
                stars = newStars,
                completedActivities = newCompleted,
                masteredTopicsCsv = masteredSet.joinToString(","),
                lastPlayedMillis = now
            )
        )

        // Update daily learning log
        val currentDaily = dao.getDailyLog(today) ?: DailyLogEntity(dateKey = today)
        val worldsSet = currentDaily.worldsPlayedCsv
            .split(",")
            .filter { it.isNotBlank() }
            .toMutableSet()
        worldsSet.add(activity.worldId.name)

        dao.upsertDailyLog(
            currentDaily.copy(
                minutesLearned = currentDaily.minutesLearned + 1,
                questionsAnswered = currentDaily.questionsAnswered + 1,
                correctAnswers = currentDaily.correctAnswers + (if (isCorrect) 1 else 0),
                worldsPlayedCsv = worldsSet.joinToString(",")
            )
        )

        if (isDailyChallenge && isCorrect) {
            prefsRepo.markDailyChallengeCompleted(today)
        }

        refreshStreakForToday(today)
        evaluateUnlocks()
    }

    suspend fun recordStoryCompleted(storyId: String) {
        prefsRepo.markStoryCompleted(storyId)
        val now = System.currentTimeMillis()
        val today = SmartLearningEngine.todayDateKey()
        val current = dao.getWorldProgress(WorldId.STORY.name)
            ?: WorldProgressEntity(worldId = WorldId.STORY.name)
        dao.upsertWorldProgress(
            current.copy(
                points = current.points + 25,
                stars = current.stars + 2,
                completedActivities = current.completedActivities + 1,
                lastPlayedMillis = now
            )
        )
        val currentDaily = dao.getDailyLog(today) ?: DailyLogEntity(dateKey = today)
        val worldsSet = currentDaily.worldsPlayedCsv
            .split(",")
            .filter { it.isNotBlank() }
            .toMutableSet()
        worldsSet.add(WorldId.STORY.name)
        dao.upsertDailyLog(
            currentDaily.copy(
                minutesLearned = currentDaily.minutesLearned + 2,
                questionsAnswered = currentDaily.questionsAnswered + 1,
                correctAnswers = currentDaily.correctAnswers + 1,
                worldsPlayedCsv = worldsSet.joinToString(",")
            )
        )
        dao.insertAchievement(AchievementEntity("badge_story", now))
        refreshStreakForToday(today)
        evaluateUnlocks()
    }

    private suspend fun refreshStreakForToday(today: String) {
        val prefs = prefsRepo.preferencesFlow.first()
        if (prefs.lastStreakDate != today) {
            val yesterday = SmartLearningEngine.yesterdayDateKey()
            val nextStreak = if (prefs.lastStreakDate == yesterday) prefs.currentStreakDays + 1 else 1
            prefsRepo.updateStreak(today, nextStreak.coerceAtLeast(1))
        }
    }

    private suspend fun evaluateUnlocks() {
        val allProgress = dao.observeWorldProgress().first()
        val totalStars = allProgress.sumOf { it.stars }
        val totalPoints = allProgress.sumOf { it.points }

        for (badge in ContentRepository.allBadges) {
            val shouldUnlock = when {
                badge.requiredStars > 0 -> totalStars >= badge.requiredStars
                badge.requiredWorld != null -> {
                    val wp = allProgress.firstOrNull { it.worldId == badge.requiredWorld.name }
                    (wp?.completedActivities ?: 0) >= 3
                }
                else -> false
            }
            if (shouldUnlock) {
                dao.insertAchievement(AchievementEntity(badge.id))
            }
        }

        for (character in ContentRepository.allCharacters) {
            if (totalPoints >= character.requiredPoints) {
                dao.insertUnlockedCharacter(CharacterUnlockEntity(character.id))
            }
        }
    }

    suspend fun resetAllProgress() {
        dao.clearWorldProgress()
        dao.clearAttempts()
        dao.clearDailyLogs()
        dao.clearAchievements()
        dao.clearCharacters()
        prefsRepo.resetLearningCounters()
        ensureStarterCharacterUnlocked()
    }
}
