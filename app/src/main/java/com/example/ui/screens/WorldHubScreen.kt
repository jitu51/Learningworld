package com.example.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.data.content.ContentRepository
import com.example.data.model.LearningActivity
import com.example.data.model.WorldId
import com.example.ui.viewmodel.MainUiState
import com.example.ui.viewmodel.PracticeGenUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WorldHubScreen(
    worldId: WorldId,
    uiState: MainUiState,
    practiceState: PracticeGenUiState,
    onBack: () -> Unit,
    onPlayActivity: (LearningActivity) -> Unit,
    onPlayRecommended: () -> Unit,
    onOpenStories: () -> Unit,
    onOpenArtStudio: () -> Unit,
    onOpenMapExplorer: () -> Unit,
    onOpenAiTutor: (String) -> Unit,
    onGenerateAiPractice: () -> Unit,
    onClearAiPractice: () -> Unit
) {
    val activities = remember(worldId) { ContentRepository.activitiesForWorld(worldId) }
    val worldProgress = uiState.worldProgress.firstOrNull { it.worldId == worldId.name }
    val solvedIds = remember(uiState.attempts, worldId) {
        uiState.attempts.filter { it.worldId == worldId.name && it.isCorrect }
            .map { it.activityId }
            .toSet()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("${worldId.emoji} ${worldId.displayName}") },
                navigationIcon = {
                    IconButton(onClick = onBack, modifier = Modifier.testTag("world_back_button")) {
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
            if (worldId == WorldId.SCIENCE || worldId == WorldId.STORY) {
                item {
                    val bannerRes = if (worldId == WorldId.SCIENCE) {
                        R.drawable.img_space_planets_1795539036823
                    } else {
                        R.drawable.img_story_forest_1795539036943
                    }
                    Image(
                        painter = painterResource(id = bannerRes),
                        contentDescription = "${worldId.displayName} illustration",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(160.dp)
                            .clip(MaterialTheme.shapes.large),
                        contentScale = ContentScale.Crop
                    )
                }
            }

            item {
                ElevatedCard(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = worldId.accentColor.copy(alpha = 0.12f))
                ) {
                    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Text(worldId.tagline, style = MaterialTheme.typography.titleMedium)
                        Text(
                            text = "Stars: ⭐ ${worldProgress?.stars ?: 0}  •  Points: 💎 ${worldProgress?.points ?: 0}  •  Quests: ${activities.size}",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            Button(
                                onClick = onPlayRecommended,
                                modifier = Modifier
                                    .weight(1f)
                                    .defaultMinSize(minHeight = 48.dp)
                                    .testTag("play_recommended_button")
                            ) {
                                Icon(Icons.Filled.PlayArrow, contentDescription = null)
                                Spacer(modifier = Modifier.width(6.dp))
                                Text("Smart Next Quest")
                            }
                            OutlinedButton(
                                onClick = { onOpenAiTutor("Teach me something fun about ${worldId.displayName}") },
                                modifier = Modifier
                                    .defaultMinSize(minHeight = 48.dp)
                                    .testTag("ask_tutor_world_button")
                            ) {
                                Text("🦉 Ask Hoot")
                            }
                        }
                    }
                }
            }

            // World-specific interactive studio launchers
            if (worldId == WorldId.STORY) {
                item {
                    SpecialFeatureCard(
                        title = "Interactive Storybooks (10 Tales)",
                        subtitle = "Make choices with Brave Rabbit, Friendly Lion, Clever Monkey & friends!",
                        buttonText = "Open Storybooks",
                        icon = { Icon(Icons.Filled.MenuBook, contentDescription = null) },
                        tag = "open_stories_button",
                        onClick = onOpenStories
                    )
                }
            }

            if (worldId == WorldId.ART) {
                item {
                    SpecialFeatureCard(
                        title = "Drawing Board, Coloring & AI Art Studio",
                        subtitle = "Paint freely, color templates, or create 1K/2K/4K Gemini illustrations!",
                        buttonText = "Open Art Studio",
                        icon = { Icon(Icons.Filled.Palette, contentDescription = null) },
                        tag = "open_art_studio_button",
                        onClick = onOpenArtStudio
                    )
                }
            }

            if (worldId == WorldId.GK) {
                item {
                    SpecialFeatureCard(
                        title = "World Map Explorer",
                        subtitle = "Tap countries across continents, discover flags, landmarks & take map quizzes!",
                        buttonText = "Open World Map",
                        icon = { Icon(Icons.Filled.Explore, contentDescription = null) },
                        tag = "open_map_explorer_button",
                        onClick = onOpenMapExplorer
                    )
                }
            }

            // AI Bonus Practice Generator Card
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
                ) {
                    Column(modifier = Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            Icon(Icons.Filled.AutoAwesome, contentDescription = null)
                            Text("Infinite AI Bonus Quest", style = MaterialTheme.typography.titleMedium)
                        }
                        when (practiceState) {
                            is PracticeGenUiState.Idle -> {
                                Text("Want a brand-new surprise question crafted by Professor Hoot?", style = MaterialTheme.typography.bodyMedium)
                                Button(
                                    onClick = onGenerateAiPractice,
                                    modifier = Modifier.testTag("generate_ai_practice_button")
                                ) {
                                    Text("Generate New Quest")
                                }
                            }
                            is PracticeGenUiState.Loading -> {
                                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                                    CircularProgressIndicator(modifier = Modifier.size(22.dp))
                                    Text("Professor Hoot is crafting a fresh question...")
                                }
                            }
                            is PracticeGenUiState.Success -> {
                                Text("Ready: ${practiceState.activity.title}", style = MaterialTheme.typography.titleMedium)
                                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                    Button(
                                        onClick = {
                                            val act = practiceState.activity
                                            onClearAiPractice()
                                            onPlayActivity(act)
                                        },
                                        modifier = Modifier.testTag("play_ai_practice_button")
                                    ) {
                                        Text("Play Now!")
                                    }
                                    TextButton(onClick = onClearAiPractice) { Text("Dismiss") }
                                }
                            }
                            is PracticeGenUiState.Error -> {
                                Text(practiceState.message, color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.bodyMedium)
                                OutlinedButton(onClick = onGenerateAiPractice) { Text("Try Again") }
                            }
                        }
                    }
                }
            }

            item {
                Text("All ${activities.size} Quests", style = MaterialTheme.typography.titleLarge)
            }

            itemsIndexed(activities) { index, activity ->
                val isDone = activity.id in solvedIds
                ElevatedCard(
                    onClick = { onPlayActivity(activity) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("world_activity_$index")
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
                                .size(48.dp)
                                .background(worldId.accentColor.copy(alpha = 0.15f), MaterialTheme.shapes.small),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(activity.visualEmoji, fontSize = 24.sp)
                        }
                        Column(modifier = Modifier.weight(1f)) {
                            Text(activity.title, style = MaterialTheme.typography.titleMedium)
                            Text(
                                "${activity.type.label} • ${activity.difficulty.label} • ${activity.topicTag}",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                        Text(if (isDone) "✅" else "▶️", fontSize = 20.sp)
                    }
                }
            }
        }
    }
}

@Composable
private fun SpecialFeatureCard(
    title: String,
    subtitle: String,
    buttonText: String,
    icon: @Composable () -> Unit,
    tag: String,
    onClick: () -> Unit
) {
    ElevatedCard(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
    ) {
        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                icon()
                Text(title, style = MaterialTheme.typography.titleMedium)
            }
            Text(subtitle, style = MaterialTheme.typography.bodyMedium)
            Button(
                onClick = onClick,
                modifier = Modifier
                    .defaultMinSize(minHeight = 48.dp)
                    .testTag(tag)
            ) {
                Text(buttonText)
            }
        }
    }
}
