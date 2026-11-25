package com.example.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.example.data.model.WorldId
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.parentPrefsDataStore: DataStore<Preferences> by preferencesDataStore(name = "parent_settings")

data class ParentPreferences(
    val dailyTargetMinutes: Int = 30,
    val minutesPerSubject: Int = 5,
    val routineWorlds: Set<String> = setOf(
        WorldId.ENGLISH.name,
        WorldId.MATH.name,
        WorldId.BANGLA.name,
        WorldId.SCIENCE.name,
        WorldId.LOGIC.name,
        WorldId.REAL_LIFE.name
    ),
    val difficultyMode: String = "ADAPTIVE",
    val soundEffectsEnabled: Boolean = true,
    val musicEnabled: Boolean = false,
    val voiceReadAloudEnabled: Boolean = true,
    val aiFeaturesEnabled: Boolean = true,
    val tutorLanguage: String = "EN",
    val currentStreakDays: Int = 0,
    val bestStreakDays: Int = 0,
    val lastStreakDate: String = "",
    val selectedMascotId: String = "owl_hoot",
    val completedStoriesCsv: String = "",
    val dailyChallengeCompletedDate: String = ""
)

class ParentPreferencesRepository(private val context: Context) {
    private object Keys {
        val DAILY_TARGET_MINUTES = intPreferencesKey("daily_target_minutes")
        val MINUTES_PER_SUBJECT = intPreferencesKey("minutes_per_subject")
        val ROUTINE_WORLDS = stringSetPreferencesKey("routine_worlds")
        val DIFFICULTY_MODE = stringPreferencesKey("difficulty_mode")
        val SOUND_EFFECTS = booleanPreferencesKey("sound_effects")
        val MUSIC_ENABLED = booleanPreferencesKey("music_enabled")
        val VOICE_READ_ALOUD = booleanPreferencesKey("voice_read_aloud")
        val AI_FEATURES_ENABLED = booleanPreferencesKey("ai_features_enabled")
        val TUTOR_LANGUAGE = stringPreferencesKey("tutor_language")
        val STREAK_DAYS = intPreferencesKey("streak_days")
        val BEST_STREAK_DAYS = intPreferencesKey("best_streak_days")
        val LAST_STREAK_DATE = stringPreferencesKey("last_streak_date")
        val SELECTED_MASCOT = stringPreferencesKey("selected_mascot")
        val COMPLETED_STORIES = stringPreferencesKey("completed_stories")
        val DAILY_CHALLENGE_DATE = stringPreferencesKey("daily_challenge_date")
    }

    val preferencesFlow: Flow<ParentPreferences> = context.parentPrefsDataStore.data.map { prefs ->
        val defaultRoutine = ParentPreferences().routineWorlds
        ParentPreferences(
            dailyTargetMinutes = prefs[Keys.DAILY_TARGET_MINUTES] ?: 30,
            minutesPerSubject = prefs[Keys.MINUTES_PER_SUBJECT] ?: 5,
            routineWorlds = prefs[Keys.ROUTINE_WORLDS]?.takeIf { it.isNotEmpty() } ?: defaultRoutine,
            difficultyMode = prefs[Keys.DIFFICULTY_MODE] ?: "ADAPTIVE",
            soundEffectsEnabled = prefs[Keys.SOUND_EFFECTS] ?: true,
            musicEnabled = prefs[Keys.MUSIC_ENABLED] ?: false,
            voiceReadAloudEnabled = prefs[Keys.VOICE_READ_ALOUD] ?: true,
            aiFeaturesEnabled = prefs[Keys.AI_FEATURES_ENABLED] ?: true,
            tutorLanguage = prefs[Keys.TUTOR_LANGUAGE] ?: "EN",
            currentStreakDays = prefs[Keys.STREAK_DAYS] ?: 0,
            bestStreakDays = prefs[Keys.BEST_STREAK_DAYS] ?: 0,
            lastStreakDate = prefs[Keys.LAST_STREAK_DATE] ?: "",
            selectedMascotId = prefs[Keys.SELECTED_MASCOT] ?: "owl_hoot",
            completedStoriesCsv = prefs[Keys.COMPLETED_STORIES] ?: "",
            dailyChallengeCompletedDate = prefs[Keys.DAILY_CHALLENGE_DATE] ?: ""
        )
    }

    suspend fun setDailyTargetMinutes(minutes: Int) {
        context.parentPrefsDataStore.edit { it[Keys.DAILY_TARGET_MINUTES] = minutes.coerceIn(10, 90) }
    }

    suspend fun setMinutesPerSubject(minutes: Int) {
        context.parentPrefsDataStore.edit { it[Keys.MINUTES_PER_SUBJECT] = minutes.coerceIn(3, 20) }
    }

    suspend fun toggleRoutineWorld(worldName: String) {
        context.parentPrefsDataStore.edit { prefs ->
            val current = (prefs[Keys.ROUTINE_WORLDS] ?: ParentPreferences().routineWorlds).toMutableSet()
            if (current.contains(worldName)) {
                if (current.size > 1) current.remove(worldName)
            } else {
                current.add(worldName)
            }
            prefs[Keys.ROUTINE_WORLDS] = current
        }
    }

    suspend fun setDifficultyMode(mode: String) {
        context.parentPrefsDataStore.edit { it[Keys.DIFFICULTY_MODE] = mode }
    }

    suspend fun setSoundEffectsEnabled(enabled: Boolean) {
        context.parentPrefsDataStore.edit { it[Keys.SOUND_EFFECTS] = enabled }
    }

    suspend fun setMusicEnabled(enabled: Boolean) {
        context.parentPrefsDataStore.edit { it[Keys.MUSIC_ENABLED] = enabled }
    }

    suspend fun setVoiceReadAloudEnabled(enabled: Boolean) {
        context.parentPrefsDataStore.edit { it[Keys.VOICE_READ_ALOUD] = enabled }
    }

    suspend fun setAiFeaturesEnabled(enabled: Boolean) {
        context.parentPrefsDataStore.edit { it[Keys.AI_FEATURES_ENABLED] = enabled }
    }

    suspend fun setTutorLanguage(lang: String) {
        context.parentPrefsDataStore.edit { it[Keys.TUTOR_LANGUAGE] = lang }
    }

    suspend fun updateStreak(todayKey: String, newStreak: Int) {
        context.parentPrefsDataStore.edit {
            it[Keys.LAST_STREAK_DATE] = todayKey
            it[Keys.STREAK_DAYS] = newStreak
            val prevBest = it[Keys.BEST_STREAK_DAYS] ?: 0
            if (newStreak > prevBest) {
                it[Keys.BEST_STREAK_DAYS] = newStreak
            }
        }
    }

    suspend fun markStoryCompleted(storyId: String) {
        context.parentPrefsDataStore.edit { prefs ->
            val set = (prefs[Keys.COMPLETED_STORIES] ?: "")
                .split(",")
                .filter { it.isNotBlank() }
                .toMutableSet()
            set.add(storyId)
            prefs[Keys.COMPLETED_STORIES] = set.joinToString(",")
        }
    }

    suspend fun markDailyChallengeCompleted(todayKey: String) {
        context.parentPrefsDataStore.edit { it[Keys.DAILY_CHALLENGE_DATE] = todayKey }
    }

    suspend fun resetLearningCounters() {
        context.parentPrefsDataStore.edit {
            it[Keys.STREAK_DAYS] = 0
            it[Keys.BEST_STREAK_DAYS] = 0
            it[Keys.LAST_STREAK_DATE] = ""
            it[Keys.COMPLETED_STORIES] = ""
            it[Keys.DAILY_CHALLENGE_DATE] = ""
        }
    }
}
