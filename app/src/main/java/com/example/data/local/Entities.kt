package com.example.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "world_progress")
data class WorldProgressEntity(
    @PrimaryKey val worldId: String,
    val points: Int = 0,
    val stars: Int = 0,
    val completedActivities: Int = 0,
    val masteredTopicsCsv: String = "",
    val lastPlayedMillis: Long = 0L
)

@Entity(tableName = "question_attempts")
data class QuestionAttemptEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val activityId: String,
    val worldId: String,
    val topicTag: String,
    val isCorrect: Boolean,
    val isVocabulary: Boolean,
    val timestampMillis: Long = System.currentTimeMillis()
)

@Entity(tableName = "daily_logs")
data class DailyLogEntity(
    @PrimaryKey val dateKey: String,
    val minutesLearned: Int = 0,
    val questionsAnswered: Int = 0,
    val correctAnswers: Int = 0,
    val worldsPlayedCsv: String = ""
)

@Entity(tableName = "achievements")
data class AchievementEntity(
    @PrimaryKey val badgeId: String,
    val unlockedAtMillis: Long = System.currentTimeMillis()
)

@Entity(tableName = "unlocked_characters")
data class CharacterUnlockEntity(
    @PrimaryKey val characterId: String,
    val unlockedAtMillis: Long = System.currentTimeMillis()
)
