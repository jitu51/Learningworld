package com.example.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.engine.DailyStreakInfo
import com.example.ui.theme.MeadowGreen
import com.example.ui.theme.SunburstAmber
import com.example.ui.theme.WarmGold

@Composable
fun DailyStreakCard(
    streakInfo: DailyStreakInfo,
    modifier: Modifier = Modifier,
    onActionClick: (() -> Unit)? = null
) {
    val pulseTransition = rememberInfiniteTransition(label = "streakFlamePulse")
    val flameScale by pulseTransition.animateFloat(
        initialValue = 0.94f,
        targetValue = 1.08f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 950, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "flameScale"
    )

    val daysToNext = (streakInfo.nextMilestoneDays - streakInfo.activeStreakDays).coerceAtLeast(1)
    val milestoneProgress = (streakInfo.activeStreakDays.toFloat() / streakInfo.nextMilestoneDays.coerceAtLeast(1))
        .coerceIn(0f, 1f)

    val headline = when {
        streakInfo.activeStreakDays == 0 -> "Start Your Daily Streak!"
        streakInfo.activeStreakDays == 1 -> "1-Day Learning Streak!"
        else -> "${streakInfo.activeStreakDays}-Day Learning Streak!"
    }

    val encouragement = when {
        streakInfo.completedToday -> "Awesome! Today's flame is lit—only $daysToNext more day${if (daysToNext == 1) "" else "s"} to your ${streakInfo.nextMilestoneDays}-day goal!"
        streakInfo.activeStreakDays > 0 -> "Complete 1 quest today to grow your streak to ${streakInfo.activeStreakDays + 1} days!"
        else -> "Finish any 1 fun quest today to light your very first streak flame!"
    }

    ElevatedCard(
        onClick = { onActionClick?.invoke() },
        enabled = onActionClick != null,
        modifier = modifier
            .fillMaxWidth()
            .testTag("daily_streak_card"),
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Brush.linearGradient(
                        listOf(
                            SunburstAmber.copy(alpha = 0.16f),
                            WarmGold.copy(alpha = 0.10f),
                            MaterialTheme.colorScheme.surface
                        )
                    )
                )
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Animated Flame Emblem
                Box(
                    modifier = Modifier
                        .size(54.dp)
                        .scale(flameScale)
                        .clip(CircleShape)
                        .background(
                            if (streakInfo.completedToday) SunburstAmber else SunburstAmber.copy(alpha = 0.22f)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Filled.LocalFireDepartment,
                        contentDescription = "Daily learning streak flame",
                        tint = if (streakInfo.completedToday) MaterialTheme.colorScheme.onPrimary else SunburstAmber,
                        modifier = Modifier.size(32.dp)
                    )
                }

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = headline,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = if (streakInfo.completedToday) "Today's Goal Lit ✨" else "Waiting for Today's Quest",
                        style = MaterialTheme.typography.labelLarge,
                        color = if (streakInfo.completedToday) MeadowGreen else SunburstAmber
                    )
                }

                if (streakInfo.bestStreakDays > 0) {
                    Surface(
                        shape = MaterialTheme.shapes.small,
                        color = MaterialTheme.colorScheme.secondaryContainer
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Star,
                                contentDescription = null,
                                tint = SunburstAmber,
                                modifier = Modifier.size(16.dp)
                            )
                            Text(
                                text = "Best ${streakInfo.bestStreakDays}d",
                                style = MaterialTheme.typography.labelLarge,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }

            // 7-Day Habit Strip
            if (streakInfo.weekDays.isNotEmpty()) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    streakInfo.weekDays.forEachIndexed { idx, day ->
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(4.dp),
                            modifier = Modifier.testTag("streak_day_$idx")
                        ) {
                            val circleBg = when {
                                day.isCompleted -> SunburstAmber
                                day.isToday -> MaterialTheme.colorScheme.secondaryContainer
                                else -> MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.55f)
                            }
                            Box(
                                modifier = Modifier
                                    .size(36.dp)
                                    .clip(CircleShape)
                                    .background(circleBg)
                                    .then(
                                        if (day.isToday && !day.isCompleted) {
                                            Modifier.border(
                                                BorderStroke(2.dp, SunburstAmber),
                                                CircleShape
                                            )
                                        } else {
                                            Modifier
                                        }
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = when {
                                        day.isCompleted -> "🔥"
                                        day.isToday -> "✨"
                                        else -> "·"
                                    },
                                    fontSize = if (day.isCompleted || day.isToday) 16.sp else 18.sp
                                )
                            }
                            Text(
                                text = if (day.isToday) "Today" else day.dayInitial,
                                style = MaterialTheme.typography.labelLarge,
                                fontWeight = if (day.isToday) FontWeight.Bold else FontWeight.Medium,
                                color = if (day.isToday) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }

            // Milestone progress bar
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                LinearProgressIndicator(
                    progress = { milestoneProgress },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp)
                        .clip(CircleShape),
                    color = SunburstAmber,
                    trackColor = MaterialTheme.colorScheme.surfaceVariant
                )
                Text(
                    text = encouragement,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}
