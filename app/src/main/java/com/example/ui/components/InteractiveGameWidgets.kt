@file:OptIn(ExperimentalLayoutApi::class)

package com.example.ui.components

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.example.data.model.LearningActivity
import com.example.engine.AudioVoiceManager
import kotlinx.coroutines.delay

@Composable
fun MultipleChoiceWidget(
    activity: LearningActivity,
    isSolved: Boolean,
    selectedOption: String?,
    onSelectOption: (String) -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        activity.options.forEachIndexed { idx, option ->
            val isSelected = selectedOption == option
            val isCorrectChoice = option == activity.correctAnswer
            val containerColor by animateColorAsState(
                targetValue = when {
                    isSolved && isCorrectChoice -> MaterialTheme.colorScheme.tertiaryContainer
                    isSelected && !isCorrectChoice -> MaterialTheme.colorScheme.error.copy(alpha = 0.14f)
                    isSelected -> MaterialTheme.colorScheme.primaryContainer
                    else -> MaterialTheme.colorScheme.surface
                },
                label = "optionColor"
            )
            Card(
                onClick = { if (!isSolved) onSelectOption(option) },
                modifier = Modifier
                    .fillMaxWidth()
                    .defaultMinSize(minHeight = 56.dp)
                    .testTag("quiz_option_$idx"),
                shape = MaterialTheme.shapes.medium,
                colors = CardDefaults.cardColors(containerColor = containerColor),
                border = BorderStroke(
                    width = if (isSelected || (isSolved && isCorrectChoice)) 2.dp else 1.dp,
                    color = when {
                        isSolved && isCorrectChoice -> MaterialTheme.colorScheme.tertiary
                        isSelected -> MaterialTheme.colorScheme.primary
                        else -> MaterialTheme.colorScheme.outline
                    }
                )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 14.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = option,
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.weight(1f)
                    )
                    if (isSolved && isCorrectChoice) {
                        Icon(
                            imageVector = Icons.Filled.CheckCircle,
                            contentDescription = "Correct choice",
                            tint = MaterialTheme.colorScheme.tertiary
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun DragSortWidget(
    activity: LearningActivity,
    onCompleted: (Boolean) -> Unit
) {
    val placements = remember(activity.id) { mutableStateMapOf<String, String>() }
    var activeItem by remember(activity.id) {
        mutableStateOf(activity.dragItems.firstOrNull()?.first)
    }

    Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
        Text(
            text = "Step 1: Pick a chip below, then tap its matching basket!",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            activity.dragItems.forEachIndexed { idx, (label, _) ->
                val placedBucket = placements[label]
                val isSelected = activeItem == label
                FilterChip(
                    selected = isSelected || placedBucket != null,
                    onClick = { activeItem = label },
                    modifier = Modifier
                        .defaultMinSize(minHeight = 48.dp)
                        .testTag("drag_item_$idx"),
                    label = {
                        Text(
                            text = if (placedBucket != null) "$label → $placedBucket" else label,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                )
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            activity.buckets.forEachIndexed { bIdx, bucket ->
                ElevatedCard(
                    onClick = {
                        val current = activeItem ?: return@ElevatedCard
                        placements[current] = bucket
                        activeItem = activity.dragItems.firstOrNull { it.first !in placements.keys }?.first
                        if (placements.size == activity.dragItems.size) {
                            val allRight = activity.dragItems.all { (item, expected) -> placements[item] == expected }
                            onCompleted(allRight)
                        }
                    },
                    modifier = Modifier
                        .weight(1f)
                        .defaultMinSize(minHeight = 88.dp)
                        .testTag("sort_bucket_$bIdx"),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(14.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text("🧺 $bucket", style = MaterialTheme.typography.titleMedium)
                        Spacer(modifier = Modifier.height(4.dp))
                        val count = placements.values.count { it == bucket }
                        Text("$count items", style = MaterialTheme.typography.bodyMedium)
                    }
                }
            }
        }

        OutlinedButton(
            onClick = {
                placements.clear()
                activeItem = activity.dragItems.firstOrNull()?.first
            },
            modifier = Modifier.testTag("reset_sort_button")
        ) {
            Icon(Icons.Filled.Refresh, contentDescription = null)
            Spacer(modifier = Modifier.width(6.dp))
            Text("Reset Baskets")
        }
    }
}

@Composable
fun WordBuildWidget(
    activity: LearningActivity,
    onCompleted: (Boolean) -> Unit
) {
    var builtIndices by remember(activity.id) { mutableStateOf<List<Int>>(emptyList()) }
    val builtWord = builtIndices.joinToString("") { activity.options[it] }

    Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
        ) {
            Text(
                text = if (builtWord.isEmpty()) "Tap tiles in order..." else builtWord,
                style = MaterialTheme.typography.headlineMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp)
                    .testTag("built_word_preview")
            )
        }

        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            activity.options.forEachIndexed { idx, tile ->
                val used = idx in builtIndices
                FilledTonalButton(
                    onClick = {
                        if (!used) {
                            val next = builtIndices + idx
                            builtIndices = next
                            if (next.size == activity.options.size) {
                                val attempt = next.joinToString("") { activity.options[it] }
                                onCompleted(attempt.equals(activity.correctAnswer, ignoreCase = true))
                            }
                        }
                    },
                    enabled = !used,
                    modifier = Modifier
                        .defaultMinSize(minWidth = 60.dp, minHeight = 56.dp)
                        .testTag("word_tile_$idx")
                ) {
                    Text(tile, style = MaterialTheme.typography.titleLarge)
                }
            }
        }

        OutlinedButton(
            onClick = { builtIndices = emptyList() },
            modifier = Modifier.testTag("clear_word_button")
        ) {
            Icon(Icons.Filled.Refresh, contentDescription = null)
            Spacer(modifier = Modifier.width(6.dp))
            Text("Clear Tiles")
        }
    }
}

@Composable
fun PairMatchWidget(
    activity: LearningActivity,
    onCompleted: (Boolean) -> Unit
) {
    val leftItems = remember(activity.id) { activity.matchPairs.map { it.first } }
    val rightItems = remember(activity.id) { activity.matchPairs.map { it.second }.shuffled() }
    var selectedLeft by remember(activity.id) { mutableStateOf<String?>(null) }
    val matchedLefts = remember(activity.id) { mutableStateListOf<String>() }

    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        Text(
            text = "Tap one card on the left, then its matching partner on the right!",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                leftItems.forEachIndexed { idx, left ->
                    val isMatched = left in matchedLefts
                    val isSelected = selectedLeft == left
                    OutlinedCard(
                        onClick = { if (!isMatched) selectedLeft = left },
                        modifier = Modifier
                            .fillMaxWidth()
                            .defaultMinSize(minHeight = 56.dp)
                            .testTag("pair_left_$idx"),
                        colors = CardDefaults.cardColors(
                            containerColor = when {
                                isMatched -> MaterialTheme.colorScheme.tertiaryContainer
                                isSelected -> MaterialTheme.colorScheme.primaryContainer
                                else -> MaterialTheme.colorScheme.surface
                            }
                        )
                    ) {
                        Box(modifier = Modifier.fillMaxWidth().padding(12.dp), contentAlignment = Alignment.Center) {
                            Text(left, style = MaterialTheme.typography.titleMedium, textAlign = TextAlign.Center)
                        }
                    }
                }
            }

            Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                rightItems.forEachIndexed { idx, right ->
                    val pairedLeft = activity.matchPairs.firstOrNull { it.second == right }?.first
                    val isMatched = pairedLeft != null && pairedLeft in matchedLefts
                    OutlinedCard(
                        onClick = {
                            val currentLeft = selectedLeft ?: return@OutlinedCard
                            val expectedRight = activity.matchPairs.firstOrNull { it.first == currentLeft }?.second
                            if (expectedRight == right) {
                                matchedLefts.add(currentLeft)
                                selectedLeft = null
                                if (matchedLefts.size == activity.matchPairs.size) {
                                    onCompleted(true)
                                }
                            } else {
                                onCompleted(false)
                                selectedLeft = null
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .defaultMinSize(minHeight = 56.dp)
                            .testTag("pair_right_$idx"),
                        colors = CardDefaults.cardColors(
                            containerColor = if (isMatched) MaterialTheme.colorScheme.tertiaryContainer else MaterialTheme.colorScheme.surface
                        )
                    ) {
                        Box(modifier = Modifier.fillMaxWidth().padding(12.dp), contentAlignment = Alignment.Center) {
                            Text(right, style = MaterialTheme.typography.titleMedium, textAlign = TextAlign.Center)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MemoryCardsWidget(
    activity: LearningActivity,
    onCompleted: (Boolean) -> Unit
) {
    val deck = remember(activity.id) {
        activity.memoryPairs.flatMap { listOf(it.first, it.second) }.shuffled()
    }
    var flippedIndices by remember(activity.id) { mutableStateOf<List<Int>>(emptyList()) }
    var matchedIndices by remember(activity.id) { mutableStateOf<Set<Int>>(emptySet()) }
    var moves by remember(activity.id) { mutableIntStateOf(0) }

    LaunchedEffect(flippedIndices) {
        if (flippedIndices.size == 2) {
            delay(650)
            val (first, second) = flippedIndices
            if (deck[first] == deck[second]) {
                val updated = matchedIndices + first + second
                matchedIndices = updated
                if (updated.size == deck.size) {
                    onCompleted(true)
                }
            }
            flippedIndices = emptyList()
        }
    }

    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        Text("Moves: $moves • Pairs Found: ${matchedIndices.size / 2}/${deck.size / 2}", style = MaterialTheme.typography.titleMedium)

        LazyVerticalGrid(
            columns = GridCells.Fixed(if (deck.size <= 6) 3 else 4),
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(max = 260.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(deck.indices.toList()) { idx ->
                val isRevealed = idx in flippedIndices || idx in matchedIndices
                ElevatedCard(
                    onClick = {
                        if (!isRevealed && flippedIndices.size < 2) {
                            flippedIndices = flippedIndices + idx
                            if (flippedIndices.size == 2) moves++
                        }
                    },
                    modifier = Modifier
                        .height(72.dp)
                        .testTag("memory_card_$idx"),
                    colors = CardDefaults.cardColors(
                        containerColor = if (idx in matchedIndices) MaterialTheme.colorScheme.tertiaryContainer
                        else if (isRevealed) MaterialTheme.colorScheme.primaryContainer
                        else MaterialTheme.colorScheme.secondaryContainer
                    )
                ) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(
                            text = if (isRevealed) deck[idx] else "❓",
                            fontSize = 28.sp
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Sudoku4x4Widget(
    activity: LearningActivity,
    onCompleted: (Boolean) -> Unit
) {
    val initialGrid = activity.sudokuGrid
    val userGrid = remember(activity.id) { mutableStateListOf<Int>().apply { addAll(initialGrid) } }
    var selectedCell by remember(activity.id) {
        mutableIntStateOf(initialGrid.indexOfFirst { it == 0 }.coerceAtLeast(0))
    }

    Column(verticalArrangement = Arrangement.spacedBy(12.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        for (row in 0 until 4) {
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                for (col in 0 until 4) {
                    val idx = row * 4 + col
                    val isLocked = initialGrid.getOrElse(idx) { 0 } != 0
                    val value = userGrid.getOrElse(idx) { 0 }
                    val isSelected = selectedCell == idx
                    OutlinedCard(
                        onClick = { if (!isLocked) selectedCell = idx },
                        modifier = Modifier
                            .size(56.dp)
                            .testTag("sudoku_cell_$idx"),
                        colors = CardDefaults.cardColors(
                            containerColor = when {
                                isLocked -> MaterialTheme.colorScheme.surfaceVariant
                                isSelected -> MaterialTheme.colorScheme.primaryContainer
                                else -> MaterialTheme.colorScheme.surface
                            }
                        ),
                        border = BorderStroke(if (isSelected) 2.5.dp else 1.dp, MaterialTheme.colorScheme.primary)
                    ) {
                        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                            Text(
                                text = if (value == 0) "" else value.toString(),
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = if (isLocked) FontWeight.Bold else FontWeight.SemiBold
                            )
                        }
                    }
                }
            }
        }

        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            listOf(1, 2, 3, 4).forEach { num ->
                FilledTonalButton(
                    onClick = {
                        if (initialGrid.getOrElse(selectedCell) { 0 } == 0) {
                            userGrid[selectedCell] = num
                            if (userGrid.none { it == 0 }) {
                                onCompleted(userGrid.toList() == activity.sudokuSolution)
                            }
                        }
                    },
                    modifier = Modifier
                        .defaultMinSize(minWidth = 54.dp, minHeight = 52.dp)
                        .testTag("sudoku_key_$num")
                ) {
                    Text(num.toString(), style = MaterialTheme.typography.titleLarge)
                }
            }
        }
    }
}

@Composable
fun SequenceRecallWidget(
    activity: LearningActivity,
    onCompleted: (Boolean) -> Unit
) {
    var showingPattern by remember(activity.id) { mutableStateOf(true) }
    var tapped by remember(activity.id) { mutableStateOf<List<String>>(emptyList()) }
    val choices = remember(activity.id) { activity.sequenceItems.shuffled() }

    LaunchedEffect(activity.id) {
        delay(2600)
        showingPattern = false
    }

    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
        ) {
            Column(modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = if (showingPattern) "Memorize this order!" else "Now tap the icons in order!",
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.height(10.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    if (showingPattern) {
                        activity.sequenceItems.forEach { Text(it, fontSize = 32.sp) }
                    } else {
                        activity.sequenceItems.indices.forEach { idx ->
                            Text(tapped.getOrNull(idx) ?: "⬜", fontSize = 30.sp)
                        }
                    }
                }
            }
        }

        if (!showingPattern) {
            FlowRow(horizontalArrangement = Arrangement.spacedBy(10.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                choices.forEachIndexed { idx, item ->
                    FilledTonalButton(
                        onClick = {
                            if (item !in tapped) {
                                val next = tapped + item
                                tapped = next
                                if (next.size == activity.sequenceItems.size) {
                                    onCompleted(next == activity.sequenceItems)
                                    tapped = emptyList()
                                }
                            }
                        },
                        enabled = item !in tapped,
                        modifier = Modifier
                            .defaultMinSize(minWidth = 64.dp, minHeight = 56.dp)
                            .testTag("seq_choice_$idx")
                    ) {
                        Text(item, fontSize = 26.sp)
                    }
                }
            }
            TextButton(
                onClick = { showingPattern = true; tapped = emptyList() },
                modifier = Modifier.testTag("peek_sequence_button")
            ) {
                Icon(Icons.Filled.Visibility, contentDescription = null)
                Spacer(modifier = Modifier.width(6.dp))
                Text("Peek Again")
            }
        }
    }
}

@Composable
fun SpotDifferenceWidget(
    activity: LearningActivity,
    onCompleted: (Boolean) -> Unit
) {
    val selected = remember(activity.id) { mutableStateListOf<String>() }

    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        Text(
            text = "Tap ${activity.differenceTargets.size} items that are special or changed:",
            style = MaterialTheme.typography.titleMedium
        )
        FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            activity.options.forEachIndexed { idx, item ->
                val isPicked = item in selected
                FilterChip(
                    selected = isPicked,
                    onClick = {
                        if (isPicked) selected.remove(item) else selected.add(item)
                        if (selected.size == activity.differenceTargets.size) {
                            onCompleted(selected.toSet() == activity.differenceTargets.toSet())
                        }
                    },
                    modifier = Modifier
                        .defaultMinSize(minHeight = 48.dp)
                        .testTag("spot_option_$idx"),
                    label = { Text(item) }
                )
            }
        }
    }
}

@Composable
fun VoiceSpeakWidget(
    activity: LearningActivity,
    audioVoiceManager: AudioVoiceManager,
    onCompleted: (Boolean) -> Unit
) {
    val context = LocalContext.current
    var heardText by remember(activity.id) { mutableStateOf("") }
    var statusMsg by remember(activity.id) { mutableStateOf("Tap the microphone to speak, or type below!") }
    val isBengali = activity.worldId.name == "BANGLA"

    val startListening = {
        statusMsg = "Listening..."
        audioVoiceManager.startSpeechCapture(
            isBengali = isBengali,
            onResult = { spoken ->
                heardText = spoken
                statusMsg = "Heard: \"$spoken\""
                val ok = spoken.contains(activity.correctAnswer, ignoreCase = true) || spoken.length >= 2
                onCompleted(ok)
            },
            onError = { err -> statusMsg = err }
        )
        Unit
    }

    val micPermissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { granted ->
        if (granted) startListening()
        else statusMsg = "Microphone permission not granted. You can type your answer below!"
    }

    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Button(
            onClick = {
                val hasPerm = ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.RECORD_AUDIO
                ) == PackageManager.PERMISSION_GRANTED
                if (hasPerm) startListening()
                else micPermissionLauncher.launch(Manifest.permission.RECORD_AUDIO)
            },
            modifier = Modifier
                .fillMaxWidth()
                .defaultMinSize(minHeight = 56.dp)
                .testTag("voice_speak_button")
        ) {
            Icon(Icons.Filled.Mic, contentDescription = "Speak answer")
            Spacer(modifier = Modifier.width(8.dp))
            Text("Tap to Speak")
        }

        Text(statusMsg, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)

        OutlinedTextField(
            value = heardText,
            onValueChange = { heardText = it },
            label = { Text("Or type what you said") },
            modifier = Modifier
                .fillMaxWidth()
                .testTag("voice_fallback_input"),
            singleLine = true
        )

        FilledTonalButton(
            onClick = {
                val ok = heardText.trim().contains(activity.correctAnswer, ignoreCase = true) ||
                    heardText.trim().equals("thank you", ignoreCase = true)
                onCompleted(ok)
            },
            enabled = heardText.isNotBlank(),
            modifier = Modifier.testTag("voice_check_button")
        ) {
            Text("Check My Word")
        }
    }
}
