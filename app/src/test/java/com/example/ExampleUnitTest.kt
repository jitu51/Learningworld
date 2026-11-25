package com.example

import com.example.data.content.ContentRepository
import com.example.data.local.DailyLogEntity
import com.example.data.local.ParentPreferences
import com.example.data.local.QuestionAttemptEntity
import com.example.data.local.WorldProgressEntity
import com.example.data.model.ActivityType
import com.example.data.model.DifficultyLevel
import com.example.data.model.WorldId
import com.example.engine.SmartLearningEngine
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class ExampleUnitTest {
    @Test
    fun contentCatalog_meetsAllContentVolumeRequirements() {
        val all = ContentRepository.allActivities
        assertTrue("Expected at least 150 total activities, got ${all.size}", all.size >= 150)

        for (world in WorldId.entries) {
            val count = ContentRepository.activitiesForWorld(world).size
            assertTrue("Expected >= 15 activities in $world, got $count", count >= 15)
        }

        val vocabCount = all.count { it.isVocabulary }
        assertTrue("Expected >= 50 vocabulary exercises, got $vocabCount", vocabCount >= 50)

        val mathCount = ContentRepository.activitiesForWorld(WorldId.MATH).size
        assertTrue("Expected >= 30 math challenges, got $mathCount", mathCount >= 30)

        val logicCount = ContentRepository.activitiesForWorld(WorldId.LOGIC).size
        assertTrue("Expected >= 20 logic puzzles, got $logicCount", logicCount >= 20)

        val storyCount = ContentRepository.allStories.size
        assertTrue("Expected >= 10 interactive stories, got $storyCount", storyCount >= 10)

        val memoryGameCount = all.count {
            it.type == ActivityType.MEMORY_CARDS || it.type == ActivityType.SEQUENCE_RECALL
        }
        assertTrue("Expected >= 10 memory games, got $memoryGameCount", memoryGameCount >= 10)
    }

    @Test
    fun smartLearningEngine_adaptsDifficultyAndQueuesRevision() {
        assertEquals(1, SmartLearningEngine.computeLevel(0))
        assertEquals(2, SmartLearningEngine.computeLevel(100))

        val highAccuracyAttempts = (1..5).map {
            QuestionAttemptEntity(
                activityId = "math_$it",
                worldId = WorldId.MATH.name,
                topicTag = "Addition",
                isCorrect = true,
                isVocabulary = false
            )
        }
        assertEquals(
            DifficultyLevel.MASTER,
            SmartLearningEngine.recommendedDifficulty(highAccuracyAttempts, "ADAPTIVE")
        )

        val missedAttempt = listOf(
            QuestionAttemptEntity(
                activityId = "eng_3",
                worldId = WorldId.ENGLISH.name,
                topicTag = "Alphabet A-Z",
                isCorrect = false,
                isVocabulary = true
            )
        )
        val rec = SmartLearningEngine.recommendNextActivity(
            worldProgress = emptyList<WorldProgressEntity>(),
            recentAttempts = missedAttempt,
            overrideMode = "ADAPTIVE",
            preferredWorld = WorldId.ENGLISH
        )
        assertTrue("Expected missed item to be queued for gentle revision", rec.isRevision)
        assertEquals("eng_3", rec.nextActivity.id)
    }

    @Test
    fun dailyStreak_tracksConsecutiveDaysAndSevenDayStrip() {
        val activePrefs = ParentPreferences(
            currentStreakDays = 4,
            bestStreakDays = 6,
            lastStreakDate = "2026-11-24"
        )
        val logs = listOf(
            DailyLogEntity(dateKey = "2026-11-23", minutesLearned = 5, questionsAnswered = 3, correctAnswers = 3),
            DailyLogEntity(dateKey = "2026-11-24", minutesLearned = 4, questionsAnswered = 2, correctAnswers = 2)
        )
        val info = SmartLearningEngine.computeStreakInfo(
            prefs = activePrefs,
            dailyLogs = logs,
            todayKey = "2026-11-24",
            yesterdayKey = "2026-11-23"
        )
        assertEquals(4, info.activeStreakDays)
        assertEquals(6, info.bestStreakDays)
        assertTrue(info.completedToday)
        assertEquals(7, info.nextMilestoneDays)
        assertEquals(7, info.weekDays.size)
        assertTrue(info.weekDays.last().isToday)
        assertTrue(info.weekDays.last().isCompleted)
    }
}
