package com.example.ui.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.data.content.ContentRepository
import com.example.data.model.WorldId
import com.example.ui.components.DailyStreakCard
import com.example.ui.viewmodel.MainUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeDashboardScreen(
    uiState: MainUiState,
    onStartAdventure: () -> Unit,
    onOpenDailyChallenge: () -> Unit,
    onOpenDailyPlan: () -> Unit,
    onOpenWorld: (WorldId) -> Unit,
    onOpenProgress: () -> Unit,
    onOpenBadges: () -> Unit,
    onOpenAiTutor: () -> Unit,
    onOpenParentGate: () -> Unit,
    onToggleSoundEffects: () -> Unit,
    onToggleMusic: () -> Unit,
    onToggleVoice: () -> Unit,
    onSpeakWelcome: () -> Unit
) {
    var showAudioSheet by remember { mutableStateOf(false) }

    val infiniteTransition = rememberInfiniteTransition(label = "mascotBounce")
    val bounceOffset by infiniteTransition.animateFloat(
        initialValue = -4f,
        targetValue = 6f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1200, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "mascotFloat"
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("🦉", fontSize = 24.sp)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = stringResource(R.string.app_name),
                            style = MaterialTheme.typography.titleLarge,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                },
                actions = {
                    IconButton(
                        onClick = { showAudioSheet = true },
                        modifier = Modifier.testTag("audio_settings_button")
                    ) {
                        Icon(
                            imageVector = if (uiState.preferences.soundEffectsEnabled) Icons.Filled.MusicNote else Icons.Filled.MusicOff,
                            contentDescription = "Sound and Music Settings"
                        )
                    }
                    FilledTonalIconButton(
                        onClick = onOpenParentGate,
                        modifier = Modifier.testTag("parent_zone_button")
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Lock,
                            contentDescription = stringResource(R.string.parent_dashboard)
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = onOpenAiTutor,
                icon = { Text("🦉", fontSize = 22.sp) },
                text = { Text(stringResource(R.string.ai_tutor_short)) },
                containerColor = MaterialTheme.colorScheme.secondary,
                contentColor = MaterialTheme.colorScheme.onSecondary,
                modifier = Modifier.testTag("ai_tutor_fab")
            )
        }
    ) { innerPadding ->
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        listOf(
                            MaterialTheme.colorScheme.background,
                            MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.28f),
                            MaterialTheme.colorScheme.background
                        )
                    )
                )
                .padding(innerPadding)
        ) {
            val columns = if (maxWidth > 620.dp) 3 else 2
            val progressMap = uiState.worldProgress.associateBy { it.worldId }

            LazyVerticalGrid(
                columns = GridCells.Fixed(columns),
                modifier = Modifier
                    .fillMaxSize()
                    .widthIn(max = 900.dp)
                    .align(Alignment.TopCenter),
                contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 96.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                item(span = { GridItemSpan(columns) }) {
                    HeroWelcomeBanner(
                        level = uiState.level,
                        stars = uiState.totalStars,
                        points = uiState.totalPoints,
                        streakDays = uiState.dailyStreak.activeStreakDays,
                        bounceOffsetDp = bounceOffset,
                        onSpeakWelcome = onSpeakWelcome,
                        onStartAdventure = onStartAdventure
                    )
                }

                item(span = { GridItemSpan(columns) }) {
                    DailyStreakCard(
                        streakInfo = uiState.dailyStreak,
                        onActionClick = onStartAdventure
                    )
                }

                item(span = { GridItemSpan(columns) }) {
                    QuickDashboardActionsRow(
                        dailyCompleted = uiState.dailyChallengeCompletedToday,
                        todayMinutes = uiState.todayMinutes,
                        dailyGoal = uiState.preferences.dailyTargetMinutes,
                        badgesCount = uiState.achievements.size,
                        onOpenDailyChallenge = onOpenDailyChallenge,
                        onOpenDailyPlan = onOpenDailyPlan,
                        onOpenProgress = onOpenProgress,
                        onOpenBadges = onOpenBadges
                    )
                }

                item(span = { GridItemSpan(columns) }) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 6.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "10 Learning Worlds",
                            style = MaterialTheme.typography.headlineMedium
                        )
                        Text(
                            text = "Ages 5–8",
                            style = MaterialTheme.typography.labelLarge,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }

                itemsIndexed(WorldId.entries) { idx, world ->
                    val wp = progressMap[world.name]
                    val totalInWorld = ContentRepository.activitiesForWorld(world).size.coerceAtLeast(1)
                    val completed = (wp?.completedActivities ?: 0).coerceAtMost(totalInWorld)
                    WorldCard(
                        world = world,
                        completed = completed,
                        total = totalInWorld,
                        stars = wp?.stars ?: 0,
                        modifier = Modifier.testTag("world_card_$idx"),
                        onClick = { onOpenWorld(world) }
                    )
                }
            }
        }
    }

    if (showAudioSheet) {
        ModalBottomSheet(onDismissRequest = { showAudioSheet = false }) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 12.dp)
                    .padding(bottom = 28.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text("Sound & Music Settings", style = MaterialTheme.typography.headlineMedium)

                AudioToggleRow(
                    title = "Playful Sound Effects",
                    subtitle = "Chimes when you solve puzzles",
                    checked = uiState.preferences.soundEffectsEnabled,
                    onCheckedChange = { onToggleSoundEffects() },
                    tag = "toggle_sfx"
                )
                AudioToggleRow(
                    title = "Calm Background Music",
                    subtitle = "Gentle storybook melody",
                    checked = uiState.preferences.musicEnabled,
                    onCheckedChange = { onToggleMusic() },
                    tag = "toggle_music"
                )
                AudioToggleRow(
                    title = "Voice Read-Aloud",
                    subtitle = "Professor Hoot reads questions aloud",
                    checked = uiState.preferences.voiceReadAloudEnabled,
                    onCheckedChange = { onToggleVoice() },
                    tag = "toggle_voice"
                )
            }
        }
    }
}

@Composable
private fun HeroWelcomeBanner(
    level: Int,
    stars: Int,
    points: Int,
    streakDays: Int,
    bounceOffsetDp: Float,
    onSpeakWelcome: () -> Unit,
    onStartAdventure: () -> Unit
) {
    ElevatedCard(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(176.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.img_hero_world_1795539036545),
                    contentDescription = "Kids Learning World adventure island",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                listOf(
                                    MaterialTheme.colorScheme.primary.copy(alpha = 0.15f),
                                    MaterialTheme.colorScheme.surface.copy(alpha = 0.88f)
                                )
                            )
                        )
                )

                // Floating animated cartoon mascots row
                Row(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(12.dp)
                        .offset(y = bounceOffsetDp.dp),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    listOf("🦉", "🐰", "🦁", "🐒").forEach { mascot ->
                        Surface(
                            shape = CircleShape,
                            color = MaterialTheme.colorScheme.surface.copy(alpha = 0.92f),
                            shadowElevation = 2.dp
                        ) {
                            Text(
                                text = mascot,
                                fontSize = 20.sp,
                                modifier = Modifier.padding(6.dp)
                            )
                        }
                    }
                }

                Column(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(16.dp)
                ) {
                    Text(
                        text = stringResource(R.string.welcome_banner_title),
                        style = MaterialTheme.typography.headlineMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = stringResource(R.string.welcome_banner_subtitle),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                StatPill("⭐ $stars", Modifier.weight(1f))
                StatPill("🏆 Lv.$level", Modifier.weight(1f))
                StatPill("💎 $points", Modifier.weight(1f))
                StatPill("🔥 ${streakDays}d", Modifier.weight(1f))
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = onStartAdventure,
                    modifier = Modifier
                        .weight(1f)
                        .defaultMinSize(minHeight = 52.dp)
                        .testTag("start_adventure_button"),
                    shape = MaterialTheme.shapes.medium
                ) {
                    Icon(Icons.Filled.PlayArrow, contentDescription = null)
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(stringResource(R.string.start_adventure), style = MaterialTheme.typography.titleMedium)
                }

                FilledTonalIconButton(
                    onClick = onSpeakWelcome,
                    modifier = Modifier
                        .size(52.dp)
                        .testTag("welcome_listen_button")
                ) {
                    Icon(Icons.AutoMirrored.Filled.VolumeUp, contentDescription = "Listen to welcome message")
                }
            }
        }
    }
}

@Composable
private fun StatPill(label: String, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier,
        shape = MaterialTheme.shapes.small,
        color = MaterialTheme.colorScheme.primaryContainer
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp),
            maxLines = 1
        )
    }
}

@Composable
private fun QuickDashboardActionsRow(
    dailyCompleted: Boolean,
    todayMinutes: Int,
    dailyGoal: Int,
    badgesCount: Int,
    onOpenDailyChallenge: () -> Unit,
    onOpenDailyPlan: () -> Unit,
    onOpenProgress: () -> Unit,
    onOpenBadges: () -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            DashboardQuickCard(
                emoji = if (dailyCompleted) "✅" else "⚡",
                title = stringResource(R.string.daily_challenge),
                subtitle = if (dailyCompleted) "Completed today!" else "+25 Bonus Pts",
                containerColor = MaterialTheme.colorScheme.secondaryContainer,
                modifier = Modifier
                    .weight(1f)
                    .testTag("daily_challenge_card"),
                onClick = onOpenDailyChallenge
            )
            DashboardQuickCard(
                emoji = "⏱️",
                title = stringResource(R.string.daily_plan_title),
                subtitle = "$todayMinutes / $dailyGoal min",
                containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                modifier = Modifier
                    .weight(1f)
                    .testTag("daily_plan_card"),
                onClick = onOpenDailyPlan
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            DashboardQuickCard(
                emoji = "📊",
                title = stringResource(R.string.my_progress),
                subtitle = "Levels & Topics",
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                modifier = Modifier
                    .weight(1f)
                    .testTag("my_progress_card"),
                onClick = onOpenProgress
            )
            DashboardQuickCard(
                emoji = "🏅",
                title = stringResource(R.string.achievements),
                subtitle = "$badgesCount Badges",
                containerColor = MaterialTheme.colorScheme.surfaceVariant,
                modifier = Modifier
                    .weight(1f)
                    .testTag("achievements_card"),
                onClick = onOpenBadges
            )
        }
    }
}

@Composable
private fun DashboardQuickCard(
    emoji: String,
    title: String,
    subtitle: String,
    containerColor: androidx.compose.ui.graphics.Color,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = modifier.defaultMinSize(minHeight = 74.dp),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(containerColor = containerColor)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(emoji, fontSize = 26.sp)
            Column {
                Text(title, style = MaterialTheme.typography.titleMedium, maxLines = 1, overflow = TextOverflow.Ellipsis)
                Text(subtitle, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant, maxLines = 1)
            }
        }
    }
}

@Composable
private fun WorldCard(
    world: WorldId,
    completed: Int,
    total: Int,
    stars: Int,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    ElevatedCard(
        onClick = onClick,
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(46.dp)
                        .clip(CircleShape)
                        .background(world.accentColor.copy(alpha = 0.16f)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(world.emoji, fontSize = 24.sp)
                }
                Text("⭐ $stars", style = MaterialTheme.typography.labelLarge, fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = world.displayName,
                style = MaterialTheme.typography.titleMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = world.tagline,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.heightIn(min = 38.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            LinearProgressIndicator(
                progress = { completed.toFloat() / total.toFloat() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp)
                    .clip(CircleShape),
                color = world.accentColor
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "$completed / $total quests",
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun AudioToggleRow(
    title: String,
    subtitle: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    tag: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(title, style = MaterialTheme.typography.titleMedium)
            Text(subtitle, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            modifier = Modifier.testTag(tag)
        )
    }
}
