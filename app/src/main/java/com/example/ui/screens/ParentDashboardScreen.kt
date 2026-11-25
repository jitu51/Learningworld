package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.DeleteForever
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.unit.dp
import com.example.BuildConfig
import com.example.R
import com.example.data.model.WorldId
import com.example.ui.viewmodel.MainUiState

@Composable
fun ParentGateDialog(
    onUnlocked: () -> Unit,
    onDismiss: () -> Unit
) {
    val pair = remember {
        val a = (6..9).random()
        val b = (4..8).random()
        a to b
    }
    var answerText by remember { mutableStateOf("") }
    var errorMsg by remember { mutableStateOf<String?>(null) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(stringResource(R.string.parent_gate_title)) },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                Text(stringResource(R.string.parent_gate_subtitle))
                Text(
                    text = "What is ${pair.first} × ${pair.second}?",
                    style = MaterialTheme.typography.headlineMedium
                )
                OutlinedTextField(
                    value = answerText,
                    onValueChange = { answerText = it.filter { ch -> ch.isDigit() } },
                    label = { Text("Enter answer") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    singleLine = true,
                    modifier = Modifier.testTag("parent_gate_input")
                )
                errorMsg?.let {
                    Text(it, color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.bodyMedium)
                }
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    if (answerText.toIntOrNull() == pair.first * pair.second) {
                        onUnlocked()
                    } else {
                        errorMsg = "Try again! (${pair.first} × ${pair.second})"
                    }
                },
                modifier = Modifier.testTag("parent_gate_submit")
            ) {
                Text("Unlock")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) { Text("Cancel") }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun ParentDashboardScreen(
    uiState: MainUiState,
    onSetDailyTarget: (Int) -> Unit,
    onSetMinutesPerSubject: (Int) -> Unit,
    onToggleRoutineSubject: (String) -> Unit,
    onSetDifficultyMode: (String) -> Unit,
    onToggleVoice: () -> Unit,
    onToggleAiFeatures: (Boolean) -> Unit,
    onResetProgress: () -> Unit,
    onBack: () -> Unit
) {
    var showResetConfirm by remember { mutableStateOf(false) }
    var showPrivacyDialog by remember { mutableStateOf(false) }
    var copiedNotice by remember { mutableStateOf(false) }
    @Suppress("DEPRECATION")
    val clipboardManager = LocalClipboardManager.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("👨‍👩‍👧 Parent Dashboard") },
                navigationIcon = {
                    IconButton(onClick = onBack, modifier = Modifier.testTag("parent_back_button")) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            item {
                ElevatedCard(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
                ) {
                    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Text("Child Learning Summary", style = MaterialTheme.typography.titleLarge)
                        Text("• Today's Learning Time: ${uiState.todayMinutes} min / ${uiState.preferences.dailyTargetMinutes} min goal")
                        Text("• Completed Quests: ${uiState.worldProgress.sumOf { it.completedActivities }}")
                        Text("• Overall Accuracy: ${uiState.overallAccuracyPercent}%")
                        Text("• Vocabulary Mastered: ${uiState.vocabularyCount} words")
                        Text("• Current Streak: ${uiState.dailyStreak.activeStreakDays} day(s) (Best: ${uiState.dailyStreak.bestStreakDays}d) • Badges: ${uiState.achievements.size}")

                        OutlinedButton(
                            onClick = {
                                val summary = buildString {
                                    appendLine("Kids Learning World - Progress Report")
                                    appendLine("Level: ${uiState.level} | Points: ${uiState.totalPoints} | Stars: ${uiState.totalStars}")
                                    appendLine("Today's Time: ${uiState.todayMinutes}/${uiState.preferences.dailyTargetMinutes} min")
                                    appendLine("Accuracy: ${uiState.overallAccuracyPercent}% | Vocabulary: ${uiState.vocabularyCount} words")
                                    appendLine("Streak: ${uiState.dailyStreak.activeStreakDays} day(s) (Best: ${uiState.dailyStreak.bestStreakDays}d)")
                                }
                                clipboardManager.setText(AnnotatedString(summary))
                                copiedNotice = true
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .defaultMinSize(minHeight = 48.dp)
                                .testTag("copy_report_button")
                        ) {
                            Icon(Icons.Filled.ContentCopy, contentDescription = null)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(stringResource(R.string.copy_report_button))
                        }
                        if (copiedNotice) {
                            Text(
                                text = stringResource(R.string.report_copied_msg),
                                style = MaterialTheme.typography.labelLarge,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                }
            }

            // 7-day learning activity bar visualization
            item {
                Card(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Text("Recent Daily Activity (Minutes)", style = MaterialTheme.typography.titleMedium)
                        val logs = uiState.dailyLogs.take(7).reversed()
                        if (logs.isEmpty()) {
                            Text("No recorded sessions yet — play any world to start tracking!", style = MaterialTheme.typography.bodyMedium)
                        } else {
                            val maxMins = (logs.maxOfOrNull { it.minutesLearned } ?: 1).coerceAtLeast(1)
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(110.dp),
                                horizontalArrangement = Arrangement.SpaceEvenly,
                                verticalAlignment = Alignment.Bottom
                            ) {
                                logs.forEach { log ->
                                    val ratio = (log.minutesLearned.toFloat() / maxMins).coerceIn(0.12f, 1f)
                                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                        Text("${log.minutesLearned}m", style = MaterialTheme.typography.labelLarge)
                                        Box(
                                            modifier = Modifier
                                                .width(24.dp)
                                                .height((72 * ratio).dp)
                                                .clip(MaterialTheme.shapes.small)
                                                .background(MaterialTheme.colorScheme.primary)
                                        )
                                        Text(log.dateKey.takeLast(5), style = MaterialTheme.typography.bodyMedium)
                                    }
                                }
                            }
                        }
                    }
                }
            }

            // Areas Needing Attention
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
                ) {
                    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                        Text("Areas Needing Gentle Practice", style = MaterialTheme.typography.titleMedium)
                        if (uiState.weakTopics.isEmpty()) {
                            Text("All attempted topics are above 75% accuracy! Great progress!", style = MaterialTheme.typography.bodyMedium)
                        } else {
                            uiState.weakTopics.forEach { (topic, acc) ->
                                Text("• $topic — $acc% accuracy", style = MaterialTheme.typography.bodyMedium)
                            }
                        }
                    }
                }
            }

            // Daily Goal & Routine Settings
            item {
                Card(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                        Text("Daily Learning Routine", style = MaterialTheme.typography.titleMedium)
                        Text("Daily Time Limit Goal: ${uiState.preferences.dailyTargetMinutes} minutes")
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            listOf(15, 30, 45, 60).forEach { mins ->
                                FilterChip(
                                    selected = uiState.preferences.dailyTargetMinutes == mins,
                                    onClick = { onSetDailyTarget(mins) },
                                    label = { Text("${mins}m") },
                                    modifier = Modifier.testTag("parent_goal_$mins")
                                )
                            }
                        }

                        Text("Minutes per Subject: ${uiState.preferences.minutesPerSubject} minutes")
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            listOf(5, 10, 15).forEach { mins ->
                                FilterChip(
                                    selected = uiState.preferences.minutesPerSubject == mins,
                                    onClick = { onSetMinutesPerSubject(mins) },
                                    label = { Text("${mins}m") }
                                )
                            }
                        }

                        Text("Active Daily Subjects", style = MaterialTheme.typography.titleMedium)
                        FlowRow(horizontalArrangement = Arrangement.spacedBy(6.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                            WorldId.entries.forEach { world ->
                                FilterChip(
                                    selected = world.name in uiState.preferences.routineWorlds,
                                    onClick = { onToggleRoutineSubject(world.name) },
                                    label = { Text("${world.emoji} ${world.shortLabel}") }
                                )
                            }
                        }
                    }
                }
            }

            // Difficulty & AI & Voice Controls
            item {
                Card(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                        Text("Difficulty & Safety Controls", style = MaterialTheme.typography.titleMedium)
                        FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            listOf("ADAPTIVE", "BEGINNER", "EXPLORER", "MASTER").forEach { mode ->
                                FilterChip(
                                    selected = uiState.preferences.difficultyMode == mode,
                                    onClick = { onSetDifficultyMode(mode) },
                                    label = { Text(mode.lowercase().replaceFirstChar { it.uppercase() }) },
                                    modifier = Modifier.testTag("parent_diff_${mode.lowercase()}")
                                )
                            }
                        }

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text("Voice Read-Aloud", style = MaterialTheme.typography.titleMedium)
                                Text("Automatically read questions aloud", style = MaterialTheme.typography.bodyMedium)
                            }
                            Switch(
                                checked = uiState.preferences.voiceReadAloudEnabled,
                                onCheckedChange = { onToggleVoice() },
                                modifier = Modifier.testTag("parent_voice_switch")
                            )
                        }

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text("Cloud AI Tutor & AI Art Studio", style = MaterialTheme.typography.titleMedium)
                                Text("Uses Firebase AI Logic (offline fallback if off)", style = MaterialTheme.typography.bodyMedium)
                            }
                            Switch(
                                checked = uiState.preferences.aiFeaturesEnabled,
                                onCheckedChange = onToggleAiFeatures,
                                modifier = Modifier.testTag("parent_ai_switch")
                            )
                        }
                    }
                }
            }

            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer)
                ) {
                    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Icon(Icons.Filled.VerifiedUser, contentDescription = null)
                            Text(
                                text = stringResource(R.string.parent_privacy_title),
                                style = MaterialTheme.typography.titleMedium
                            )
                        }
                        Text(
                            text = stringResource(R.string.parent_privacy_summary),
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Text(
                            text = "App Version ${BuildConfig.VERSION_NAME} • Package: ${BuildConfig.APPLICATION_ID}",
                            style = MaterialTheme.typography.labelLarge,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        OutlinedButton(
                            onClick = { showPrivacyDialog = true },
                            modifier = Modifier
                                .fillMaxWidth()
                                .defaultMinSize(minHeight = 48.dp)
                                .testTag("view_privacy_details_button")
                        ) {
                            Text(stringResource(R.string.view_data_safety_button))
                        }
                    }
                }
            }

            item {
                OutlinedButton(
                    onClick = { showResetConfirm = true },
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = MaterialTheme.colorScheme.error),
                    modifier = Modifier
                        .fillMaxWidth()
                        .defaultMinSize(minHeight = 52.dp)
                        .testTag("parent_reset_button")
                ) {
                    Icon(Icons.Filled.DeleteForever, contentDescription = null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Reset Child Learning Progress")
                }
            }
        }
    }

    if (showPrivacyDialog) {
        AlertDialog(
            onDismissRequest = { showPrivacyDialog = false },
            title = { Text(stringResource(R.string.privacy_modal_title)) },
            text = { Text(stringResource(R.string.privacy_modal_body)) },
            confirmButton = {
                Button(onClick = { showPrivacyDialog = false }) {
                    Text("Close")
                }
            }
        )
    }

    if (showResetConfirm) {
        AlertDialog(
            onDismissRequest = { showResetConfirm = false },
            title = { Text("Reset Progress?") },
            text = { Text("This will clear points, stars, streaks, and badges on this device.") },
            confirmButton = {
                Button(
                    onClick = {
                        onResetProgress()
                        showResetConfirm = false
                    },
                    modifier = Modifier.testTag("confirm_reset_button")
                ) {
                    Text("Reset")
                }
            },
            dismissButton = {
                TextButton(onClick = { showResetConfirm = false }) { Text("Keep Data") }
            }
        )
    }
}
