package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.content.ContentRepository
import com.example.data.model.MapLandmark
import com.example.engine.AudioVoiceManager
import com.example.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WorldMapExplorerScreen(
    audioVoiceManager: AudioVoiceManager,
    onBack: () -> Unit
) {
    val landmarks = ContentRepository.mapLandmarks
    var selectedLandmark by remember { mutableStateOf(landmarks.first()) }
    var quizFeedback by remember(selectedLandmark.id) { mutableStateOf<String?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("🗺️ World Map Explorer") },
                navigationIcon = {
                    IconButton(onClick = onBack, modifier = Modifier.testTag("map_back_button")) {
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
                InteractiveStorybookWorldMap(
                    landmarks = landmarks,
                    selectedLandmark = selectedLandmark,
                    onSelectLandmark = { lm ->
                        selectedLandmark = lm
                        audioVoiceManager.speak("${lm.country}! ${lm.name}")
                    }
                )
            }

            item {
                ElevatedCard(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
                ) {
                    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "${selectedLandmark.flagEmoji} ${selectedLandmark.country}",
                                style = MaterialTheme.typography.headlineMedium
                            )
                            IconButton(
                                onClick = { audioVoiceManager.speak(selectedLandmark.funFact) },
                                modifier = Modifier.testTag("map_listen_button")
                            ) {
                                Icon(Icons.AutoMirrored.Filled.VolumeUp, contentDescription = "Read fun fact aloud")
                            }
                        }
                        Text(
                            text = "Landmark: ${selectedLandmark.name} • Capital: ${selectedLandmark.capital} • Continent: ${selectedLandmark.continent}",
                            style = MaterialTheme.typography.titleMedium
                        )
                        Text(selectedLandmark.funFact, style = MaterialTheme.typography.bodyLarge)

                        HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))
                        Text("Quick Map Quiz: ${selectedLandmark.quizQuestion}", style = MaterialTheme.typography.titleMedium)
                        selectedLandmark.quizOptions.forEachIndexed { idx, opt ->
                            OutlinedButton(
                                onClick = {
                                    if (opt == selectedLandmark.quizCorrectAnswer) {
                                        audioVoiceManager.playSuccessChime()
                                        quizFeedback = "🎉 Spot on! ${selectedLandmark.quizCorrectAnswer} is right!"
                                    } else {
                                        audioVoiceManager.playGentleTryAgain()
                                        quizFeedback = "💛 Good guess! Check the card above and try again!"
                                    }
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .defaultMinSize(minHeight = 48.dp)
                                    .testTag("map_quiz_opt_$idx")
                            ) {
                                Text(opt)
                            }
                        }
                        quizFeedback?.let { Text(it, style = MaterialTheme.typography.bodyLarge) }
                    }
                }
            }

            item {
                Text("Explore Countries & Continents", style = MaterialTheme.typography.titleLarge)
            }

            itemsIndexed(landmarks) { idx, lm ->
                val isSelected = lm.id == selectedLandmark.id
                ElevatedCard(
                    onClick = { selectedLandmark = lm },
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("map_landmark_$idx"),
                    colors = CardDefaults.cardColors(
                        containerColor = if (isSelected) MaterialTheme.colorScheme.secondaryContainer else MaterialTheme.colorScheme.surface
                    )
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(14.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Text(lm.flagEmoji, fontSize = 30.sp)
                        Column(modifier = Modifier.weight(1f)) {
                            Text("${lm.country} — ${lm.name}", style = MaterialTheme.typography.titleMedium)
                            Text("Capital: ${lm.capital} • ${lm.continent}", style = MaterialTheme.typography.bodyMedium)
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun InteractiveStorybookWorldMap(
    landmarks: List<MapLandmark>,
    selectedLandmark: MapLandmark,
    onSelectLandmark: (MapLandmark) -> Unit
) {
    ElevatedCard(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large
    ) {
        Column {
            BoxWithConstraints(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(230.dp)
                    .clip(MaterialTheme.shapes.large)
                    .testTag("custom_world_map_canvas")
            ) {
                val mapWidth = maxWidth
                val mapHeight = maxHeight

                Canvas(modifier = Modifier.fillMaxSize()) {
                    val w = size.width
                    val h = size.height

                    // Ocean gradient
                    drawRect(
                        brush = Brush.verticalGradient(
                            listOf(SkyCerulean.copy(alpha = 0.25f), SkyTurquoise.copy(alpha = 0.35f))
                        ),
                        size = size
                    )

                    // Equator & meridian lines
                    val gridEffect = PathEffect.dashPathEffect(floatArrayOf(12f, 10f), 0f)
                    drawLine(
                        color = CloudWhite.copy(alpha = 0.45f),
                        start = Offset(0f, h * 0.5f),
                        end = Offset(w, h * 0.5f),
                        strokeWidth = 2f,
                        pathEffect = gridEffect
                    )
                    drawLine(
                        color = CloudWhite.copy(alpha = 0.45f),
                        start = Offset(w * 0.5f, 0f),
                        end = Offset(w * 0.5f, h),
                        strokeWidth = 2f,
                        pathEffect = gridEffect
                    )

                    // Stylized friendly continents
                    val landColor = MeadowGreen.copy(alpha = 0.72f)
                    val warmSand = SunburstAmber.copy(alpha = 0.55f)
                    // North America
                    drawRoundRect(landColor, Offset(w * 0.08f, h * 0.16f), Size(w * 0.23f, h * 0.28f), CornerRadius(32f, 32f))
                    // South America
                    drawRoundRect(landColor, Offset(w * 0.23f, h * 0.48f), Size(w * 0.13f, h * 0.32f), CornerRadius(28f, 28f))
                    // Europe
                    drawRoundRect(landColor, Offset(w * 0.45f, h * 0.16f), Size(w * 0.14f, h * 0.18f), CornerRadius(24f, 24f))
                    // Africa
                    drawRoundRect(warmSand, Offset(w * 0.45f, h * 0.36f), Size(w * 0.16f, h * 0.34f), CornerRadius(34f, 34f))
                    // Asia (including Bangladesh & Japan)
                    drawRoundRect(landColor, Offset(w * 0.59f, h * 0.15f), Size(w * 0.31f, h * 0.34f), CornerRadius(38f, 38f))
                    // Australia
                    drawRoundRect(warmSand, Offset(w * 0.76f, h * 0.59f), Size(w * 0.16f, h * 0.21f), CornerRadius(28f, 28f))
                }

                // Tappable flag pins positioned by normalized latitude & longitude
                landmarks.forEachIndexed { idx, lm ->
                    val xFraction = ((lm.longitude + 180.0) / 360.0).toFloat().coerceIn(0.08f, 0.92f)
                    val yFraction = ((90.0 - lm.latitude) / 180.0).toFloat().coerceIn(0.14f, 0.84f)
                    val isSelected = lm.id == selectedLandmark.id

                    val pinSize = if (isSelected) 48.dp else 40.dp
                    val xOffset = (mapWidth * xFraction) - (pinSize / 2)
                    val yOffset = (mapHeight * yFraction) - (pinSize / 2)

                    Surface(
                        onClick = { onSelectLandmark(lm) },
                        modifier = Modifier
                            .offset(x = xOffset, y = yOffset)
                            .size(pinSize)
                            .testTag("map_pin_$idx"),
                        shape = CircleShape,
                        color = if (isSelected) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.surface,
                        border = BorderStroke(
                            width = if (isSelected) 2.5.dp else 1.5.dp,
                            color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline
                        ),
                        shadowElevation = if (isSelected) 6.dp else 2.dp
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Text(lm.flagEmoji, fontSize = if (isSelected) 22.sp else 18.sp)
                        }
                    }
                }
            }

            Text(
                text = "Tap any flag pin on the world map to explore landmarks & take a quiz!",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(horizontal = 14.dp, vertical = 10.dp)
            )
        }
    }
}
