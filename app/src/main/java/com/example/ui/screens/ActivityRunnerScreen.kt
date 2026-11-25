package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.SkipNext
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.data.content.ContentRepository
import com.example.data.model.ActivityType
import com.example.data.model.LearningActivity
import com.example.engine.AudioVoiceManager
import com.example.ui.components.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActivityRunnerScreen(
    initialActivity: LearningActivity,
    isDailyChallenge: Boolean,
    audioVoiceManager: AudioVoiceManager,
    onBack: () -> Unit,
    onActivityResult: (LearningActivity, Boolean, Boolean) -> Unit,
    onAskTutor: (String) -> Unit
) {
    val worldList = remember(initialActivity.worldId) {
        ContentRepository.activitiesForWorld(initialActivity.worldId)
    }
    var currentActivity by remember(initialActivity.id) { mutableStateOf(initialActivity) }
    var selectedOption by remember(currentActivity.id) { mutableStateOf<String?>(null) }
    var isSolved by remember(currentActivity.id) { mutableStateOf(false) }
    var feedbackBanner by remember(currentActivity.id) { mutableStateOf<Pair<Boolean, String>?>(null) }
    var showHint by remember(currentActivity.id) { mutableStateOf(false) }

    val handleOutcome: (Boolean) -> Unit = { isCorrect ->
        onActivityResult(currentActivity, isCorrect, isDailyChallenge)
        if (isCorrect) {
            isSolved = true
            audioVoiceManager.playSuccessChime()
            feedbackBanner = true to currentActivity.explanation
            audioVoiceManager.speak(currentActivity.explanation, isBengali = currentActivity.worldId.name == "BANGLA")
        } else {
            audioVoiceManager.playGentleTryAgain()
            feedbackBanner = false to "Almost! Give it another gentle try or tap Hint 💡"
        }
    }

    LaunchedEffect(currentActivity.id) {
        audioVoiceManager.speak(
            currentActivity.speechText,
            isBengali = currentActivity.worldId.name == "BANGLA"
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(currentActivity.title, maxLines = 1) },
                navigationIcon = {
                    IconButton(onClick = onBack, modifier = Modifier.testTag("activity_back_button")) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    FilledTonalIconButton(
                        onClick = {
                            audioVoiceManager.speak(
                                currentActivity.speechText,
                                isBengali = currentActivity.worldId.name == "BANGLA"
                            )
                        },
                        modifier = Modifier.testTag("listen_question_button")
                    ) {
                        Icon(
                            Icons.AutoMirrored.Filled.VolumeUp,
                            contentDescription = stringResource(R.string.listen_button)
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Activity Prompt Card
            ElevatedCard(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
            ) {
                Column(
                    modifier = Modifier.padding(18.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        AssistChip(
                            onClick = {},
                            label = { Text("${currentActivity.worldId.shortLabel} • ${currentActivity.difficulty.label}") }
                        )
                        Text(currentActivity.visualEmoji, fontSize = 36.sp)
                    }
                    Text(currentActivity.prompt, style = MaterialTheme.typography.titleLarge)
                    if (currentActivity.promptBn.isNotBlank()) {
                        Text(
                            text = currentActivity.promptBn,
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }

            // Playable Interactive Mode Switcher
            when (currentActivity.type) {
                ActivityType.MULTIPLE_CHOICE,
                ActivityType.PICTURE_MATCH,
                ActivityType.AUDIO_LISTEN,
                ActivityType.FILL_BLANK,
                ActivityType.ANIMATED_SCENE,
                ActivityType.INTERACTIVE_STORY -> {
                    MultipleChoiceWidget(
                        activity = currentActivity,
                        isSolved = isSolved,
                        selectedOption = selectedOption,
                        onSelectOption = { choice ->
                            selectedOption = choice
                            handleOutcome(choice == currentActivity.correctAnswer)
                        }
                    )
                }
                ActivityType.DRAG_SORT -> DragSortWidget(currentActivity, handleOutcome)
                ActivityType.WORD_BUILD -> WordBuildWidget(currentActivity, handleOutcome)
                ActivityType.PAIR_MATCH -> PairMatchWidget(currentActivity, handleOutcome)
                ActivityType.MEMORY_CARDS -> MemoryCardsWidget(currentActivity, handleOutcome)
                ActivityType.SUDOKU_4X4 -> Sudoku4x4Widget(currentActivity, handleOutcome)
                ActivityType.SEQUENCE_RECALL -> SequenceRecallWidget(currentActivity, handleOutcome)
                ActivityType.SPOT_DIFFERENCE -> SpotDifferenceWidget(currentActivity, handleOutcome)
                ActivityType.VOICE_SPEAK -> VoiceSpeakWidget(currentActivity, audioVoiceManager, handleOutcome)
            }

            // Hint & Ask Professor Hoot actions
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                OutlinedButton(
                    onClick = { showHint = !showHint },
                    modifier = Modifier
                        .weight(1f)
                        .defaultMinSize(minHeight = 48.dp)
                        .testTag("hint_button")
                ) {
                    Icon(Icons.Filled.Lightbulb, contentDescription = null)
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(stringResource(R.string.hint_button))
                }
                OutlinedButton(
                    onClick = {
                        onAskTutor("Help me understand this question gently: ${currentActivity.prompt}")
                    },
                    modifier = Modifier
                        .weight(1f)
                        .defaultMinSize(minHeight = 48.dp)
                        .testTag("activity_ask_hoot_button")
                ) {
                    Text("🦉 Ask Hoot")
                }
            }

            AnimatedVisibility(visible = showHint) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
                ) {
                    Text(
                        text = "💡 Hint: ${currentActivity.hint}",
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(14.dp)
                    )
                }
            }

            // Encouraging Feedback Banner + Next Quest CTA
            feedbackBanner?.let { (isCorrect, message) ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = if (isCorrect) {
                            MaterialTheme.colorScheme.tertiaryContainer
                        } else {
                            MaterialTheme.colorScheme.secondaryContainer
                        }
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Text(
                            text = if (isCorrect) "🎉 Amazing! +10 Points ⭐" else "💛 Keep Going!",
                            style = MaterialTheme.typography.titleMedium
                        )
                        Text(message, style = MaterialTheme.typography.bodyLarge)
                        if (isCorrect && currentActivity.explanationBn.isNotBlank()) {
                            Text(currentActivity.explanationBn, style = MaterialTheme.typography.bodyMedium)
                        }
                        if (isCorrect) {
                            val idx = worldList.indexOfFirst { it.id == currentActivity.id }
                            val nextActivity = if (idx >= 0 && idx + 1 < worldList.size) {
                                worldList[idx + 1]
                            } else {
                                worldList.first()
                            }
                            Button(
                                onClick = { currentActivity = nextActivity },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .defaultMinSize(minHeight = 52.dp)
                                    .testTag("next_activity_button")
                            ) {
                                Icon(Icons.Filled.SkipNext, contentDescription = null)
                                Spacer(modifier = Modifier.width(6.dp))
                                Text("Next Quest: ${nextActivity.title}")
                            }
                        }
                    }
                }
            }
        }
    }
}
