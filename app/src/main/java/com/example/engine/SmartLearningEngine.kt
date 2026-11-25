package com.example.engine

import com.example.data.content.ContentRepository
import com.example.data.local.DailyLogEntity
import com.example.data.local.ParentPreferences
import com.example.data.local.QuestionAttemptEntity
import com.example.data.local.WorldProgressEntity
import com.example.data.model.DifficultyLevel
import com.example.data.model.LearningActivity
import com.example.data.model.WorldId
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

data class AdventureRecommendation(
    val nextActivity: LearningActivity,
    val reason: String,
    val isRevision: Boolean
)

data class DailyPlanSlot(
    val worldId: WorldId,
    val targetMinutes: Int,
    val completedToday: Boolean
)

data class StreakDayStatus(
    val dateKey: String,
    val dayInitial: String,
    val isToday: Boolean,
    val isCompleted: Boolean
)

data class DailyStreakInfo(
    val activeStreakDays: Int,
    val bestStreakDays: Int,
    val completedToday: Boolean,
    val nextMilestoneDays: Int,
    val weekDays: List<StreakDayStatus>
)

object SmartLearningEngine {

    fun computeLevel(totalPoints: Int): Int {
        return 1 + (totalPoints.coerceAtLeast(0) / 100)
    }

    fun recommendedDifficulty(
        worldAttempts: List<QuestionAttemptEntity>,
        overrideMode: String
    ): DifficultyLevel {
        when (overrideMode.uppercase()) {
            "BEGINNER" -> return DifficultyLevel.BEGINNER
            "EXPLORER" -> return DifficultyLevel.EXPLORER
            "MASTER" -> return DifficultyLevel.MASTER
        }
        val recent = worldAttempts.take(8)
        if (recent.size < 3) return DifficultyLevel.BEGINNER
        val accuracy = recent.count { it.isCorrect }.toFloat() / recent.size.toFloat()
        return when {
            accuracy >= 0.85f && recent.size >= 5 -> DifficultyLevel.MASTER
            accuracy >= 0.65f -> DifficultyLevel.EXPLORER
            else -> DifficultyLevel.BEGINNER
        }
    }

    fun recommendNextActivity(
        worldProgress: List<WorldProgressEntity>,
        recentAttempts: List<QuestionAttemptEntity>,
        overrideMode: String,
        preferredWorld: WorldId? = null
    ): AdventureRecommendation {
        val scopedAttempts = if (preferredWorld != null) {
            recentAttempts.filter { it.worldId == preferredWorld.name }
        } else {
            recentAttempts
        }

        // 1. Check for any missed activity not yet mastered afterwards (gentle spaced revision)
        val solvedIds = scopedAttempts.filter { it.isCorrect }.map { it.activityId }.toSet()
        val missedAttempt = scopedAttempts.firstOrNull { !it.isCorrect && it.activityId !in solvedIds }
        if (missedAttempt != null) {
            val missedActivity = ContentRepository.activityById(missedAttempt.activityId)
            if (missedActivity != null) {
                return AdventureRecommendation(
                    nextActivity = missedActivity,
                    reason = "Gentle review in ${missedActivity.topicTag} to build confidence!",
                    isRevision = true
                )
            }
        }

        // 2. Choose target world (either preferred or least played)
        val targetWorld = preferredWorld ?: run {
            val progressMap = worldProgress.associateBy { it.worldId }
            WorldId.entries.minByOrNull { progressMap[it.name]?.completedActivities ?: 0 } ?: WorldId.ENGLISH
        }

        val worldAttempts = recentAttempts.filter { it.worldId == targetWorld.name }
        val targetDiff = recommendedDifficulty(worldAttempts, overrideMode)
        val pool = ContentRepository.activitiesForWorld(targetWorld)

        val unplayedMatchingDiff = pool.firstOrNull { it.difficulty == targetDiff && it.id !in solvedIds }
        if (unplayedMatchingDiff != null) {
            return AdventureRecommendation(
                nextActivity = unplayedMatchingDiff,
                reason = "Matched to your ${targetDiff.label} level in ${targetWorld.displayName}!",
                isRevision = false
            )
        }

        val anyUnplayed = pool.firstOrNull { it.id !in solvedIds }
        if (anyUnplayed != null) {
            return AdventureRecommendation(
                nextActivity = anyUnplayed,
                reason = "Fresh adventure in ${targetWorld.displayName}!",
                isRevision = false
            )
        }

        return AdventureRecommendation(
            nextActivity = pool.first(),
            reason = "Replay a favorite ${targetWorld.shortLabel} quest for bonus stars!",
            isRevision = false
        )
    }

    fun weakTopics(attempts: List<QuestionAttemptEntity>): List<Pair<String, Int>> {
        return attempts
            .groupBy { "${it.worldId} • ${it.topicTag}" }
            .mapNotNull { (key, list) ->
                val acc = ((list.count { it.isCorrect } * 100f) / list.size.coerceAtLeast(1)).toInt()
                if (acc < 75) key to acc else null
            }
            .sortedBy { it.second }
            .take(5)
    }

    fun buildDailyPlan(
        prefs: ParentPreferences,
        worldsPlayedToday: Set<String>
    ): List<DailyPlanSlot> {
        val selectedWorlds = prefs.routineWorlds
            .mapNotNull { name -> WorldId.entries.firstOrNull { it.name == name } }
            .ifEmpty {
                listOf(WorldId.ENGLISH, WorldId.MATH, WorldId.BANGLA, WorldId.SCIENCE, WorldId.LOGIC, WorldId.REAL_LIFE)
            }
        return selectedWorlds.map { world ->
            DailyPlanSlot(
                worldId = world,
                targetMinutes = prefs.minutesPerSubject,
                completedToday = worldsPlayedToday.contains(world.name)
            )
        }
    }

    fun computeStreakInfo(
        prefs: ParentPreferences,
        dailyLogs: List<DailyLogEntity>,
        todayKey: String = todayDateKey(),
        yesterdayKey: String = yesterdayDateKey()
    ): DailyStreakInfo {
        val logMap = dailyLogs.associateBy { it.dateKey }
        val completedToday = prefs.lastStreakDate == todayKey ||
            ((logMap[todayKey]?.questionsAnswered ?: 0) > 0)

        val activeStreak = when {
            prefs.lastStreakDate == todayKey || prefs.lastStreakDate == yesterdayKey ->
                prefs.currentStreakDays.coerceAtLeast(if (completedToday) 1 else 0)
            completedToday -> prefs.currentStreakDays.coerceAtLeast(1)
            else -> 0
        }
        val bestStreak = maxOf(prefs.bestStreakDays, activeStreak)
        val nextMilestone = listOf(3, 7, 14, 30, 60, 100)
            .firstOrNull { it > activeStreak } ?: (activeStreak + 30)

        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.US)
        val dayFmt = SimpleDateFormat("EEEEE", Locale.US)
        val parsedToday = runCatching { sdf.parse(todayKey) }.getOrNull() ?: Date()
        val weekDays = (6 downTo 0).map { daysAgo ->
            val cal = Calendar.getInstance().apply {
                time = parsedToday
                add(Calendar.DAY_OF_YEAR, -daysAgo)
            }
            val key = sdf.format(cal.time)
            val initial = dayFmt.format(cal.time).uppercase(Locale.US).take(1)
            val hasActivity = (logMap[key]?.questionsAnswered ?: 0) > 0 || key == prefs.lastStreakDate
            StreakDayStatus(
                dateKey = key,
                dayInitial = initial,
                isToday = key == todayKey,
                isCompleted = hasActivity
            )
        }

        return DailyStreakInfo(
            activeStreakDays = activeStreak,
            bestStreakDays = bestStreak,
            completedToday = completedToday,
            nextMilestoneDays = nextMilestone,
            weekDays = weekDays
        )
    }

    fun todayDateKey(): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.US)
        return sdf.format(Date())
    }

    fun yesterdayDateKey(): String {
        val cal = Calendar.getInstance()
        cal.add(Calendar.DAY_OF_YEAR, -1)
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.US)
        return sdf.format(cal.time)
    }
}
