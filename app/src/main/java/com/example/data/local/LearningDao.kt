package com.example.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface LearningDao {
    @Query("SELECT * FROM world_progress")
    fun observeWorldProgress(): Flow<List<WorldProgressEntity>>

    @Query("SELECT * FROM world_progress WHERE worldId = :worldId LIMIT 1")
    suspend fun getWorldProgress(worldId: String): WorldProgressEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertWorldProgress(entity: WorldProgressEntity)

    @Query("SELECT * FROM question_attempts ORDER BY timestampMillis DESC LIMIT 200")
    fun observeRecentAttempts(): Flow<List<QuestionAttemptEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAttempt(attempt: QuestionAttemptEntity)

    @Query("SELECT * FROM daily_logs ORDER BY dateKey DESC LIMIT 14")
    fun observeDailyLogs(): Flow<List<DailyLogEntity>>

    @Query("SELECT * FROM daily_logs WHERE dateKey = :dateKey LIMIT 1")
    suspend fun getDailyLog(dateKey: String): DailyLogEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertDailyLog(log: DailyLogEntity)

    @Query("SELECT * FROM achievements ORDER BY unlockedAtMillis DESC")
    fun observeAchievements(): Flow<List<AchievementEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAchievement(achievement: AchievementEntity)

    @Query("SELECT * FROM unlocked_characters ORDER BY unlockedAtMillis ASC")
    fun observeUnlockedCharacters(): Flow<List<CharacterUnlockEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUnlockedCharacter(character: CharacterUnlockEntity)

    @Query("DELETE FROM world_progress")
    suspend fun clearWorldProgress()

    @Query("DELETE FROM question_attempts")
    suspend fun clearAttempts()

    @Query("DELETE FROM daily_logs")
    suspend fun clearDailyLogs()

    @Query("DELETE FROM achievements")
    suspend fun clearAchievements()

    @Query("DELETE FROM unlocked_characters")
    suspend fun clearCharacters()
}
