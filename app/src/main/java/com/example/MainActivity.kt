package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.*
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.ai.configureAppCheck
import com.example.data.content.ContentRepository
import com.example.data.model.LearningActivity
import com.example.data.model.WorldId
import com.example.engine.SmartLearningEngine
import com.example.ui.navigation.*
import com.example.ui.screens.*
import com.example.ui.theme.KidsLearningWorldTheme
import com.example.ui.viewmodel.AiTutorViewModel
import com.example.ui.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // STEP 1: Configure Firebase App Check with launch intent before UI or ViewModels
        configureAppCheck(this, intent)

        enableEdgeToEdge()
        setContent {
            KidsLearningWorldTheme {
                LearningWorldApp()
            }
        }
    }
}

@Composable
private fun LearningWorldApp(
    mainViewModel: MainViewModel = viewModel(),
    aiTutorViewModel: AiTutorViewModel = viewModel()
) {
    val navController = rememberNavController()
    val uiState by mainViewModel.uiState.collectAsStateWithLifecycle()
    val tutorState by aiTutorViewModel.tutorState.collectAsStateWithLifecycle()
    val practiceState by aiTutorViewModel.practiceState.collectAsStateWithLifecycle()
    val imageGenState by aiTutorViewModel.imageGenState.collectAsStateWithLifecycle()

    var showParentGate by remember { mutableStateOf(false) }
    var transientAiActivity by remember { mutableStateOf<LearningActivity?>(null) }

    NavHost(
        navController = navController,
        startDestination = HomeRoute
    ) {
        composable<HomeRoute> {
            HomeDashboardScreen(
                uiState = uiState,
                onStartAdventure = {
                    val rec = uiState.nextAdventure
                    if (rec != null) {
                        navController.navigate(
                            ActivityPlayRoute(
                                worldIdName = rec.nextActivity.worldId.name,
                                activityId = rec.nextActivity.id,
                                isDailyChallenge = false
                            )
                        )
                    }
                },
                onOpenDailyChallenge = {
                    val daily = uiState.dailyChallenge
                    navController.navigate(
                        ActivityPlayRoute(
                            worldIdName = daily.worldId.name,
                            activityId = daily.id,
                            isDailyChallenge = true
                        )
                    )
                },
                onOpenDailyPlan = { navController.navigate(DailyPlanRoute) },
                onOpenWorld = { world -> navController.navigate(WorldHubRoute(world.name)) },
                onOpenProgress = { navController.navigate(ProgressRoute) },
                onOpenBadges = { navController.navigate(BadgesRoute) },
                onOpenAiTutor = { navController.navigate(AiTutorRoute()) },
                onOpenParentGate = { showParentGate = true },
                onToggleSoundEffects = { mainViewModel.toggleSoundEffects() },
                onToggleMusic = { mainViewModel.toggleBackgroundMusic() },
                onToggleVoice = { mainViewModel.toggleVoiceReadAloud() },
                onSpeakWelcome = {
                    mainViewModel.audioVoiceManager.speak(
                        "Welcome to Your Learning World! Pick any adventure to play and learn!",
                        isBengali = false
                    )
                }
            )
        }

        composable<WorldHubRoute> { entry ->
            val route = entry.toRoute<WorldHubRoute>()
            val worldId = WorldId.entries.firstOrNull { it.name == route.worldIdName } ?: WorldId.ENGLISH
            val worldAttempts = uiState.attempts.filter { it.worldId == worldId.name }
            val adaptiveDiff = SmartLearningEngine.recommendedDifficulty(
                worldAttempts,
                uiState.preferences.difficultyMode
            )

            WorldHubScreen(
                worldId = worldId,
                uiState = uiState,
                practiceState = practiceState,
                onBack = { navController.popBackStack() },
                onPlayActivity = { activity ->
                    if (ContentRepository.activityById(activity.id) == null) {
                        transientAiActivity = activity
                    }
                    navController.navigate(
                        ActivityPlayRoute(worldIdName = worldId.name, activityId = activity.id)
                    )
                },
                onPlayRecommended = {
                    val rec = mainViewModel.recommendForWorld(worldId)
                    navController.navigate(
                        ActivityPlayRoute(worldIdName = worldId.name, activityId = rec.nextActivity.id)
                    )
                },
                onOpenStories = { navController.navigate(StoryAdventureRoute()) },
                onOpenArtStudio = { navController.navigate(ArtStudioRoute) },
                onOpenMapExplorer = { navController.navigate(MapExplorerRoute) },
                onOpenAiTutor = { ctx -> navController.navigate(AiTutorRoute(ctx)) },
                onGenerateAiPractice = {
                    aiTutorViewModel.generatePracticeQuestion(
                        worldId = worldId,
                        difficulty = adaptiveDiff,
                        aiEnabledByParent = uiState.preferences.aiFeaturesEnabled
                    )
                },
                onClearAiPractice = { aiTutorViewModel.clearPracticeState() }
            )
        }

        composable<ActivityPlayRoute> { entry ->
            val route = entry.toRoute<ActivityPlayRoute>()
            val worldId = WorldId.entries.firstOrNull { it.name == route.worldIdName } ?: WorldId.ENGLISH
            val activity = ContentRepository.activityById(route.activityId)
                ?: transientAiActivity?.takeIf { it.id == route.activityId }
                ?: ContentRepository.activitiesForWorld(worldId).first()

            ActivityRunnerScreen(
                initialActivity = activity,
                isDailyChallenge = route.isDailyChallenge,
                audioVoiceManager = mainViewModel.audioVoiceManager,
                onBack = { navController.popBackStack() },
                onActivityResult = { act, isCorrect, isDaily ->
                    mainViewModel.recordActivityResult(act, isCorrect, isDaily)
                },
                onAskTutor = { ctx -> navController.navigate(AiTutorRoute(ctx)) }
            )
        }

        composable<StoryAdventureRoute> { entry ->
            val route = entry.toRoute<StoryAdventureRoute>()
            StoryAdventureScreen(
                initialStoryId = route.storyId,
                audioVoiceManager = mainViewModel.audioVoiceManager,
                onStoryCompleted = { storyId -> mainViewModel.recordStoryCompleted(storyId) },
                onBack = { navController.popBackStack() }
            )
        }

        composable<ArtStudioRoute> {
            ArtCreativityScreen(
                imageGenState = imageGenState,
                aiEnabledByParent = uiState.preferences.aiFeaturesEnabled,
                onGenerateImage = { prompt, resolution ->
                    aiTutorViewModel.generateArtImage(
                        prompt = prompt,
                        resolution = resolution,
                        aiEnabledByParent = uiState.preferences.aiFeaturesEnabled
                    )
                },
                onBack = { navController.popBackStack() }
            )
        }

        composable<MapExplorerRoute> {
            WorldMapExplorerScreen(
                audioVoiceManager = mainViewModel.audioVoiceManager,
                onBack = { navController.popBackStack() }
            )
        }

        composable<ProgressRoute> {
            MyProgressScreen(
                uiState = uiState,
                onOpenWorld = { world -> navController.navigate(WorldHubRoute(world.name)) },
                onBack = { navController.popBackStack() }
            )
        }

        composable<BadgesRoute> {
            AchievementsScreen(
                uiState = uiState,
                onBack = { navController.popBackStack() }
            )
        }

        composable<DailyPlanRoute> {
            DailyPlanScreen(
                uiState = uiState,
                onOpenWorld = { world -> navController.navigate(WorldHubRoute(world.name)) },
                onBack = { navController.popBackStack() }
            )
        }

        composable<AiTutorRoute> { entry ->
            val route = entry.toRoute<AiTutorRoute>()
            AiTutorScreen(
                initialContext = route.initialContext,
                tutorState = tutorState,
                aiEnabledByParent = uiState.preferences.aiFeaturesEnabled,
                preferBengali = uiState.preferences.tutorLanguage == "BN",
                audioVoiceManager = mainViewModel.audioVoiceManager,
                onToggleLanguage = { lang -> mainViewModel.setTutorLanguage(lang) },
                onSelectModelTier = { tier -> aiTutorViewModel.selectModelTier(tier) },
                onSendMessage = { msg ->
                    aiTutorViewModel.sendMessage(
                        prompt = msg,
                        aiEnabledByParent = uiState.preferences.aiFeaturesEnabled,
                        preferBengali = uiState.preferences.tutorLanguage == "BN"
                    )
                },
                onBack = { navController.popBackStack() }
            )
        }

        composable<ParentDashboardRoute> {
            ParentDashboardScreen(
                uiState = uiState,
                onSetDailyTarget = { mainViewModel.setDailyTargetMinutes(it) },
                onSetMinutesPerSubject = { mainViewModel.setMinutesPerSubject(it) },
                onToggleRoutineSubject = { mainViewModel.toggleRoutineSubject(it) },
                onSetDifficultyMode = { mainViewModel.setDifficultyMode(it) },
                onToggleVoice = { mainViewModel.toggleVoiceReadAloud() },
                onToggleAiFeatures = { mainViewModel.setAiFeaturesEnabled(it) },
                onResetProgress = { mainViewModel.resetAllProgress() },
                onBack = { navController.popBackStack() }
            )
        }
    }

    if (showParentGate) {
        ParentGateDialog(
            onUnlocked = {
                showParentGate = false
                navController.navigate(ParentDashboardRoute)
            },
            onDismiss = { showParentGate = false }
        )
    }
}
