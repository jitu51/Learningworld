package com.example.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.content.ContentRepository
import com.example.data.local.*
import com.example.data.model.LearningActivity
import com.example.data.model.WorldId
import com.example.data.repository.LearningRepository
import com.example.engine.AdventureRecommendation
import com.example.engine.AudioVoiceManager
import com.example.engine.DailyPlanSlot
import com.example.engine.DailyStreakInfo
import com.example.engine.SmartLearningEngine
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

data class MainUiState(
    val worldProgress: List<WorldProgressEntity> = emptyList(),
    val attempts: List<QuestionAttemptEntity> = emptyList(),
    val dailyLogs: List<DailyLogEntity> = emptyList(),
    val achievements: List<AchievementEntity> = emptyList(),
    val unlockedCharacters: List<CharacterUnlockEntity> = emptyList(),
    val preferences: ParentPreferences = ParentPreferences(),
    val totalPoints: Int = 0,
    val totalStars: Int = 0,
    val level: Int = 1,
    val nextAdventure: AdventureRecommendation? = null,
    val dailyChallenge: LearningActivity = ContentRepository.dailyChallengeForDate(SmartLearningEngine.todayDateKey()),
    val dailyChallengeCompletedToday: Boolean = false,
    val dailyPlanSlots: List<DailyPlanSlot> = emptyList(),
    val todayMinutes: Int = 0,
    val overallAccuracyPercent: Int = 100,
    val vocabularyCount: Int = 0,
    val weakTopics: List<Pair<String, Int>> = emptyList(),
    val dailyStreak: DailyStreakInfo = DailyStreakInfo(0, 0, false, 3, emptyList())
)

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val database = AppDatabase.getInstance(application)
    private val prefsRepo = ParentPreferencesRepository(application)
    private val repository = LearningRepository(database.learningDao(), prefsRepo)
    val audioVoiceManager = AudioVoiceManager(application)

    private val coreDataFlow = combine(
        repository.worldProgressFlow,
        repository.recentAttemptsFlow,
        repository.dailyLogsFlow,
        repository.achievementsFlow,
        repository.unlockedCharactersFlow
    ) { progress, attempts, dailyLogs, achievements, characters ->
        CoreTuple(progress, attempts, dailyLogs, achievements, characters)
    }

    val uiState: StateFlow<MainUiState> = combine(
        coreDataFlow,
        repository.preferencesFlow
    ) { core, prefs ->
        audioVoiceManager.soundEffectsEnabled = prefs.soundEffectsEnabled
        audioVoiceManager.voiceReadAloudEnabled = prefs.voiceReadAloudEnabled

        val todayKey = SmartLearningEngine.todayDateKey()
        val todayLog = core.dailyLogs.firstOrNull { it.dateKey == todayKey }
        val worldsPlayedToday = todayLog?.worldsPlayedCsv
            ?.split(",")
            ?.filter { it.isNotBlank() }
            ?.toSet()
            ?: emptySet()

        val totalPoints = core.worldProgress.sumOf { it.points }
        val totalStars = core.worldProgress.sumOf { it.stars }
        val totalAttempted = core.attempts.size
        val accuracy = if (totalAttempted == 0) 100 else {
            ((core.attempts.count { it.isCorrect } * 100f) / totalAttempted).toInt()
        }
        val vocabCount = core.attempts.filter { it.isCorrect && it.isVocabulary }
            .map { it.activityId }
            .distinct()
            .size

        MainUiState(
            worldProgress = core.worldProgress,
            attempts = core.attempts,
            dailyLogs = core.dailyLogs,
            achievements = core.achievements,
            unlockedCharacters = core.unlockedCharacters,
            preferences = prefs,
            totalPoints = totalPoints,
            totalStars = totalStars,
            level = SmartLearningEngine.computeLevel(totalPoints),
            nextAdventure = SmartLearningEngine.recommendNextActivity(
                worldProgress = core.worldProgress,
                recentAttempts = core.attempts,
                overrideMode = prefs.difficultyMode
            ),
            dailyChallenge = ContentRepository.dailyChallengeForDate(todayKey),
            dailyChallengeCompletedToday = prefs.dailyChallengeCompletedDate == todayKey,
            dailyPlanSlots = SmartLearningEngine.buildDailyPlan(prefs, worldsPlayedToday),
            todayMinutes = todayLog?.minutesLearned ?: 0,
            overallAccuracyPercent = accuracy,
            vocabularyCount = vocabCount,
            weakTopics = SmartLearningEngine.weakTopics(core.attempts),
            dailyStreak = SmartLearningEngine.computeStreakInfo(prefs, core.dailyLogs, todayKey)
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = MainUiState()
    )

    init {
        viewModelScope.launch {
            repository.ensureStarterCharacterUnlocked()
        }
    }

    fun recommendForWorld(worldId: WorldId): AdventureRecommendation {
        val state = uiState.value
        return SmartLearningEngine.recommendNextActivity(
            worldProgress = state.worldProgress,
            recentAttempts = state.attempts,
            overrideMode = state.preferences.difficultyMode,
            preferredWorld = worldId
        )
    }

    fun recordActivityResult(activity: LearningActivity, isCorrect: Boolean, isDailyChallenge: Boolean) {
        viewModelScope.launch {
            repository.recordActivityOutcome(activity, isCorrect, isDailyChallenge)
        }
    }

    fun recordStoryCompleted(storyId: String) {
        viewModelScope.launch {
            repository.recordStoryCompleted(storyId)
        }
    }

    fun toggleSoundEffects() {
        viewModelScope.launch {
            val next = !uiState.value.preferences.soundEffectsEnabled
            prefsRepo.setSoundEffectsEnabled(next)
            audioVoiceManager.soundEffectsEnabled = next
        }
    }

    fun toggleBackgroundMusic() {
        viewModelScope.launch {
            val next = !uiState.value.preferences.musicEnabled
            prefsRepo.setMusicEnabled(next)
            audioVoiceManager.setBackgroundMusic(next)
        }
    }

    fun toggleVoiceReadAloud() {
        viewModelScope.launch {
            val next = !uiState.value.preferences.voiceReadAloudEnabled
            prefsRepo.setVoiceReadAloudEnabled(next)
            audioVoiceManager.voiceReadAloudEnabled = next
            if (!next) audioVoiceManager.stopSpeaking()
        }
    }

    fun setTutorLanguage(lang: String) {
        viewModelScope.launch { prefsRepo.setTutorLanguage(lang) }
    }

    fun setDailyTargetMinutes(minutes: Int) {
        viewModelScope.launch { prefsRepo.setDailyTargetMinutes(minutes) }
    }

    fun setMinutesPerSubject(minutes: Int) {
        viewModelScope.launch { prefsRepo.setMinutesPerSubject(minutes) }
    }

    fun toggleRoutineSubject(worldName: String) {
        viewModelScope.launch { prefsRepo.toggleRoutineWorld(worldName) }
    }

    fun setDifficultyMode(mode: String) {
        viewModelScope.launch { prefsRepo.setDifficultyMode(mode) }
    }

    fun setAiFeaturesEnabled(enabled: Boolean) {
        viewModelScope.launch { prefsRepo.setAiFeaturesEnabled(enabled) }
    }

    fun resetAllProgress() {
        viewModelScope.launch { repository.resetAllProgress() }
    }

    override fun onCleared() {
        super.onCleared()
        audioVoiceManager.release()
    }

    private data class CoreTuple(
        val worldProgress: List<WorldProgressEntity>,
        val attempts: List<QuestionAttemptEntity>,
        val dailyLogs: List<DailyLogEntity>,
        val achievements: List<AchievementEntity>,
        val unlockedCharacters: List<CharacterUnlockEntity>
    )
}
