package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.content.ContentRepository
import com.example.data.model.WorldId
import com.example.ui.components.DailyStreakCard
import com.example.ui.viewmodel.MainUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyProgressScreen(
    uiState: MainUiState,
    onOpenWorld: (WorldId) -> Unit,
    onBack: () -> Unit
) {
    val progressMap = uiState.worldProgress.associateBy { it.worldId }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("📊 My Learning Progress") },
                navigationIcon = {
                    IconButton(onClick = onBack, modifier = Modifier.testTag("progress_back_button")) {
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
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                ElevatedCard(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
                ) {
                    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Text("Level ${uiState.level} Explorer 🌟", style = MaterialTheme.typography.headlineMedium)
                        Text(
                            text = "Points: ${uiState.totalPoints} • Stars: ${uiState.totalStars} • Accuracy: ${uiState.overallAccuracyPercent}% • Vocabulary Learned: ${uiState.vocabularyCount}",
                            style = MaterialTheme.typography.bodyLarge
                        )
                        val levelProgress = (uiState.totalPoints % 100) / 100f
                        LinearProgressIndicator(
                            progress = { levelProgress },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(10.dp)
                                .clip(CircleShape)
                        )
                        Text("${100 - (uiState.totalPoints % 100)} points to Level ${uiState.level + 1}", style = MaterialTheme.typography.bodyMedium)
                    }
                }
            }

            item {
                DailyStreakCard(streakInfo = uiState.dailyStreak)
            }

            if (uiState.weakTopics.isNotEmpty()) {
                item {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
                    ) {
                        Column(modifier = Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                            Text("💡 Recommended Gentle Review", style = MaterialTheme.typography.titleMedium)
                            uiState.weakTopics.forEach { (topic, acc) ->
                                Text("• $topic ($acc% accuracy)", style = MaterialTheme.typography.bodyMedium)
                            }
                        }
                    }
                }
            }

            item { Text("Subject Progress Across 10 Worlds", style = MaterialTheme.typography.titleLarge) }

            itemsIndexed(WorldId.entries) { idx, world ->
                val wp = progressMap[world.name]
                val total = ContentRepository.activitiesForWorld(world).size.coerceAtLeast(1)
                val completed = (wp?.completedActivities ?: 0).coerceAtMost(total)
                ElevatedCard(
                    onClick = { onOpenWorld(world) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("progress_world_$idx")
                ) {
                    Column(modifier = Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text("${world.emoji} ${world.displayName}", style = MaterialTheme.typography.titleMedium)
                            Text("⭐ ${wp?.stars ?: 0}", style = MaterialTheme.typography.titleMedium)
                        }
                        LinearProgressIndicator(
                            progress = { completed.toFloat() / total.toFloat() },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(8.dp)
                                .clip(CircleShape),
                            color = world.accentColor
                        )
                        Text("$completed of $total quests completed • ${wp?.points ?: 0} pts", style = MaterialTheme.typography.bodyMedium)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AchievementsScreen(
    uiState: MainUiState,
    onBack: () -> Unit
) {
    val unlockedBadgeIds = uiState.achievements.map { it.badgeId }.toSet()
    val unlockedCharIds = uiState.unlockedCharacters.map { it.characterId }.toSet()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("🏅 Achievement Collection") },
                navigationIcon = {
                    IconButton(onClick = onBack, modifier = Modifier.testTag("badges_back_button")) {
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
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item { Text("Unlockable Story Characters", style = MaterialTheme.typography.titleLarge) }
            items(ContentRepository.allCharacters) { character ->
                val isUnlocked = character.id in unlockedCharIds || uiState.totalPoints >= character.requiredPoints
                ElevatedCard(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = if (isUnlocked) MaterialTheme.colorScheme.tertiaryContainer else MaterialTheme.colorScheme.surface
                    )
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(14.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Text(if (isUnlocked) character.emoji else "🔒", fontSize = 32.sp)
                        Column(modifier = Modifier.weight(1f)) {
                            Text(character.name, style = MaterialTheme.typography.titleMedium)
                            Text(character.description, style = MaterialTheme.typography.bodyMedium)
                        }
                        Text(
                            text = if (isUnlocked) "Unlocked!" else "${character.requiredPoints} pts",
                            style = MaterialTheme.typography.labelLarge
                        )
                    }
                }
            }

            item { Text("Learning Badges (${unlockedBadgeIds.size}/${ContentRepository.allBadges.size})", style = MaterialTheme.typography.titleLarge) }
            items(ContentRepository.allBadges) { badge ->
                val unlocked = badge.id in unlockedBadgeIds
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = if (unlocked) MaterialTheme.colorScheme.secondaryContainer else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.45f)
                    )
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(14.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(46.dp)
                                .clip(CircleShape)
                                .background(MaterialTheme.colorScheme.surface),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(if (unlocked) badge.emoji else "❔", fontSize = 24.sp)
                        }
                        Column(modifier = Modifier.weight(1f)) {
                            Text(badge.title, style = MaterialTheme.typography.titleMedium)
                            Text(badge.description, style = MaterialTheme.typography.bodyMedium)
                        }
                        if (unlocked) {
                            Icon(Icons.Filled.CheckCircle, contentDescription = "Unlocked", tint = MaterialTheme.colorScheme.tertiary)
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DailyPlanScreen(
    uiState: MainUiState,
    onOpenWorld: (WorldId) -> Unit,
    onBack: () -> Unit
) {
    val completedCount = uiState.dailyPlanSlots.count { it.completedToday }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("⏱️ ${uiState.preferences.dailyTargetMinutes}-Minute Daily Plan") },
                navigationIcon = {
                    IconButton(onClick = onBack, modifier = Modifier.testTag("daily_plan_back_button")) {
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
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                ElevatedCard(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer)
                ) {
                    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Text(
                            text = "Today's Adventure: ${uiState.todayMinutes} / ${uiState.preferences.dailyTargetMinutes} minutes",
                            style = MaterialTheme.typography.titleLarge
                        )
                        Text(
                            text = "$completedCount of ${uiState.dailyPlanSlots.size} subjects explored today (${uiState.preferences.minutesPerSubject} min per subject)",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        LinearProgressIndicator(
                            progress = {
                                (uiState.todayMinutes.toFloat() / uiState.preferences.dailyTargetMinutes.coerceAtLeast(1)).coerceIn(0f, 1f)
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(10.dp)
                                .clip(CircleShape)
                        )
                    }
                }
            }

            item {
                DailyStreakCard(streakInfo = uiState.dailyStreak)
            }

            itemsIndexed(uiState.dailyPlanSlots) { idx, slot ->
                ElevatedCard(
                    onClick = { onOpenWorld(slot.worldId) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("daily_slot_$idx")
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(14.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Text(slot.worldId.emoji, fontSize = 30.sp)
                        Column(modifier = Modifier.weight(1f)) {
                            Text(slot.worldId.displayName, style = MaterialTheme.typography.titleMedium)
                            Text("Goal: ${slot.targetMinutes} minutes", style = MaterialTheme.typography.bodyMedium)
                        }
                        if (slot.completedToday) {
                            AssistChip(onClick = {}, label = { Text("✅ Done") })
                        } else {
                            Button(onClick = { onOpenWorld(slot.worldId) }) { Text("Play") }
                        }
                    }
                }
            }
        }
    }
}
