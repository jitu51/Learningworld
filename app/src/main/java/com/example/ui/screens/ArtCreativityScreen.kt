package com.example.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Undo
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.R
import com.example.data.model.ArtResolutionOption
import com.example.ui.theme.*
import com.example.ui.viewmodel.ImageGenUiState

private data class DrawStroke(
    val points: List<Offset>,
    val color: Color,
    val width: Float
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArtCreativityScreen(
    imageGenState: ImageGenUiState,
    aiEnabledByParent: Boolean,
    onGenerateImage: (String, ArtResolutionOption) -> Unit,
    onBack: () -> Unit
) {
    var selectedTab by remember { mutableIntStateOf(0) }
    val palette = listOf(
        BerryCoral,
        SunburstAmber,
        WarmGold,
        MeadowGreen,
        SkyCerulean,
        LavenderMagic,
        WorldStoryColor,
        SlateInk,
        CloudWhite
    )
    var activeColor by remember { mutableStateOf(SkyCerulean) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("🎨 Art & Creativity Studio") },
                navigationIcon = {
                    IconButton(onClick = onBack, modifier = Modifier.testTag("art_back_button")) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            TabRow(selectedTabIndex = selectedTab) {
                Tab(
                    selected = selectedTab == 0,
                    onClick = { selectedTab = 0 },
                    text = { Text("Coloring Book") },
                    modifier = Modifier.testTag("art_tab_coloring")
                )
                Tab(
                    selected = selectedTab == 1,
                    onClick = { selectedTab = 1 },
                    text = { Text("Draw & Paint") },
                    modifier = Modifier.testTag("art_tab_draw")
                )
                Tab(
                    selected = selectedTab == 2,
                    onClick = { selectedTab = 2 },
                    text = { Text("AI Art (1K–4K)") },
                    modifier = Modifier.testTag("art_tab_ai")
                )
            }

            when (selectedTab) {
                0 -> ColoringBookTab(palette = palette, activeColor = activeColor, onSelectColor = { activeColor = it })
                1 -> FreeDrawTab(palette = palette, activeColor = activeColor, onSelectColor = { activeColor = it })
                2 -> AiArtStudioTab(
                    imageGenState = imageGenState,
                    aiEnabledByParent = aiEnabledByParent,
                    onGenerateImage = onGenerateImage
                )
            }
        }
    }
}

@Composable
private fun ColorPaletteBar(
    palette: List<Color>,
    activeColor: Color,
    onSelectColor: (Color) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally)
    ) {
        palette.forEachIndexed { idx, color ->
            val isSelected = color == activeColor
            Box(
                modifier = Modifier
                    .size(if (isSelected) 38.dp else 32.dp)
                    .clip(CircleShape)
                    .background(color)
                    .border(
                        width = if (isSelected) 3.dp else 1.dp,
                        color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline,
                        shape = CircleShape
                    )
                    .clickable { onSelectColor(color) }
                    .testTag("palette_color_$idx")
            )
        }
    }
}

@Composable
private fun ColoringBookTab(
    palette: List<Color>,
    activeColor: Color,
    onSelectColor: (Color) -> Unit
) {
    var templateIdx by remember { mutableIntStateOf(0) }
    val regionColors = remember(templateIdx) {
        mutableStateMapOf(
            "sky" to CloudWhite,
            "body" to CloudWhite,
            "roof" to CloudWhite,
            "window" to CloudWhite
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            listOf("Cozy House 🏠", "Space Rocket 🚀").forEachIndexed { idx, label ->
                FilterChip(
                    selected = templateIdx == idx,
                    onClick = { templateIdx = idx },
                    label = { Text(label) },
                    modifier = Modifier.testTag("coloring_template_$idx")
                )
            }
        }

        Text(
            text = "Pick a color below, then tap the sky, roof, walls, or window to color!",
            style = MaterialTheme.typography.bodyMedium
        )

        ColorPaletteBar(palette = palette, activeColor = activeColor, onSelectColor = onSelectColor)

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(310.dp),
            colors = CardDefaults.cardColors(containerColor = CloudWhite)
        ) {
            val outlineColor = SlateInk
            Canvas(
                modifier = Modifier
                    .fillMaxSize()
                    .pointerInput(activeColor, templateIdx) {
                        detectTapGestures { offset ->
                            val w = size.width.toFloat()
                            val h = size.height.toFloat()
                            when {
                                offset.y < h * 0.34f && offset.x > w * 0.25f && offset.x < w * 0.75f ->
                                    regionColors["roof"] = activeColor
                                offset.x > w * 0.35f && offset.x < w * 0.65f && offset.y > h * 0.48f && offset.y < h * 0.76f ->
                                    regionColors["window"] = activeColor
                                offset.x > w * 0.22f && offset.x < w * 0.78f && offset.y >= h * 0.34f && offset.y < h * 0.88f ->
                                    regionColors["body"] = activeColor
                                else -> regionColors["sky"] = activeColor
                            }
                        }
                    }
                    .testTag("coloring_canvas")
            ) {
                val w = size.width
                val h = size.height
                drawRect(color = regionColors["sky"] ?: CloudWhite, size = size)

                if (templateIdx == 0) {
                    // House roof triangle
                    val roofPath = Path().apply {
                        moveTo(w * 0.5f, h * 0.08f)
                        lineTo(w * 0.80f, h * 0.38f)
                        lineTo(w * 0.20f, h * 0.38f)
                        close()
                    }
                    drawPath(roofPath, color = regionColors["roof"] ?: CloudWhite)
                    drawPath(roofPath, color = outlineColor, style = Stroke(width = 6f))

                    // House wall
                    drawRect(
                        color = regionColors["body"] ?: CloudWhite,
                        topLeft = Offset(w * 0.24f, h * 0.38f),
                        size = Size(w * 0.52f, h * 0.50f)
                    )
                    drawRect(
                        color = outlineColor,
                        topLeft = Offset(w * 0.24f, h * 0.38f),
                        size = Size(w * 0.52f, h * 0.50f),
                        style = Stroke(width = 6f)
                    )

                    // Door / Window
                    drawRoundRect(
                        color = regionColors["window"] ?: CloudWhite,
                        topLeft = Offset(w * 0.38f, h * 0.50f),
                        size = Size(w * 0.24f, h * 0.28f),
                        cornerRadius = CornerRadius(18f, 18f)
                    )
                    drawRoundRect(
                        color = outlineColor,
                        topLeft = Offset(w * 0.38f, h * 0.50f),
                        size = Size(w * 0.24f, h * 0.28f),
                        cornerRadius = CornerRadius(18f, 18f),
                        style = Stroke(width = 6f)
                    )
                } else {
                    // Rocket nose cone
                    val nosePath = Path().apply {
                        moveTo(w * 0.5f, h * 0.08f)
                        lineTo(w * 0.66f, h * 0.36f)
                        lineTo(w * 0.34f, h * 0.36f)
                        close()
                    }
                    drawPath(nosePath, color = regionColors["roof"] ?: CloudWhite)
                    drawPath(nosePath, color = outlineColor, style = Stroke(width = 6f))

                    // Rocket body
                    drawRoundRect(
                        color = regionColors["body"] ?: CloudWhite,
                        topLeft = Offset(w * 0.34f, h * 0.36f),
                        size = Size(w * 0.32f, h * 0.48f),
                        cornerRadius = CornerRadius(24f, 24f)
                    )
                    drawRoundRect(
                        color = outlineColor,
                        topLeft = Offset(w * 0.34f, h * 0.36f),
                        size = Size(w * 0.32f, h * 0.48f),
                        cornerRadius = CornerRadius(24f, 24f),
                        style = Stroke(width = 6f)
                    )

                    // Porthole window
                    drawCircle(
                        color = regionColors["window"] ?: CloudWhite,
                        radius = w * 0.09f,
                        center = Offset(w * 0.5f, h * 0.56f)
                    )
                    drawCircle(
                        color = outlineColor,
                        radius = w * 0.09f,
                        center = Offset(w * 0.5f, h * 0.56f),
                        style = Stroke(width = 6f)
                    )
                }
            }
        }

        OutlinedButton(
            onClick = { regionColors.keys.forEach { regionColors[it] = CloudWhite } },
            modifier = Modifier.testTag("clear_coloring_button")
        ) {
            Icon(Icons.Filled.Delete, contentDescription = null)
            Spacer(modifier = Modifier.width(6.dp))
            Text("Reset Colors")
        }
    }
}

@Composable
private fun FreeDrawTab(
    palette: List<Color>,
    activeColor: Color,
    onSelectColor: (Color) -> Unit
) {
    val strokes = remember { mutableStateListOf<DrawStroke>() }
    var currentPoints by remember { mutableStateOf<List<Offset>>(emptyList()) }
    var brushSize by remember { mutableFloatStateOf(12f) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        ColorPaletteBar(palette = palette, activeColor = activeColor, onSelectColor = onSelectColor)

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                listOf("Thin" to 8f, "Medium" to 16f, "Thick" to 26f).forEach { (label, w) ->
                    FilterChip(
                        selected = brushSize == w,
                        onClick = { brushSize = w },
                        label = { Text(label) }
                    )
                }
            }
            Row {
                IconButton(
                    onClick = { if (strokes.isNotEmpty()) strokes.removeAt(strokes.lastIndex) },
                    modifier = Modifier.testTag("draw_undo_button")
                ) {
                    Icon(Icons.Filled.Undo, contentDescription = "Undo stroke")
                }
                IconButton(
                    onClick = { strokes.clear() },
                    modifier = Modifier.testTag("draw_clear_button")
                ) {
                    Icon(Icons.Filled.Delete, contentDescription = "Clear canvas")
                }
            }
        }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            colors = CardDefaults.cardColors(containerColor = CloudWhite)
        ) {
            Canvas(
                modifier = Modifier
                    .fillMaxSize()
                    .pointerInput(activeColor, brushSize) {
                        detectDragGestures(
                            onDragStart = { start -> currentPoints = listOf(start) },
                            onDrag = { change, _ ->
                                change.consume()
                                currentPoints = currentPoints + change.position
                            },
                            onDragEnd = {
                                if (currentPoints.size >= 2) {
                                    strokes.add(DrawStroke(currentPoints, activeColor, brushSize))
                                }
                                currentPoints = emptyList()
                            },
                            onDragCancel = { currentPoints = emptyList() }
                        )
                    }
                    .testTag("freehand_draw_canvas")
            ) {
                (strokes + DrawStroke(currentPoints, activeColor, brushSize)).forEach { stroke ->
                    if (stroke.points.size >= 2) {
                        val path = Path().apply {
                            moveTo(stroke.points.first().x, stroke.points.first().y)
                            stroke.points.drop(1).forEach { pt -> lineTo(pt.x, pt.y) }
                        }
                        drawPath(
                            path = path,
                            color = stroke.color,
                            style = Stroke(
                                width = stroke.width,
                                cap = StrokeCap.Round,
                                join = StrokeJoin.Round
                            )
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun AiArtStudioTab(
    imageGenState: ImageGenUiState,
    aiEnabledByParent: Boolean,
    onGenerateImage: (String, ArtResolutionOption) -> Unit
) {
    var prompt by remember { mutableStateOf("A friendly baby dragon painting a rainbow in a flower garden") }
    var selectedRes by remember { mutableStateOf(ArtResolutionOption.RES_1K) }
    val ideas = listOf(
        "A happy rabbit astronaut on the moon",
        "A friendly lion reading a storybook under a tree",
        "A colorful butterfly garden in Bangladesh"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        if (!aiEnabledByParent) {
            Card(colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)) {
                Text(
                    text = stringResource(R.string.ai_disabled_by_parent),
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(14.dp)
                )
            }
        }

        OutlinedTextField(
            value = prompt,
            onValueChange = { prompt = it },
            label = { Text("Describe your picture idea") },
            modifier = Modifier
                .fillMaxWidth()
                .testTag("ai_art_prompt_input"),
            maxLines = 3
        )

        FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            ideas.forEachIndexed { idx, idea ->
                AssistChip(
                    onClick = { prompt = idea },
                    label = { Text(idea, maxLines = 1) },
                    modifier = Modifier.testTag("ai_art_idea_$idx")
                )
            }
        }

        Text("Picture Resolution", style = MaterialTheme.typography.titleMedium)
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            ArtResolutionOption.entries.forEach { res ->
                FilterChip(
                    selected = selectedRes == res,
                    onClick = { selectedRes = res },
                    label = { Text("${res.label} (${res.pixelsHint})") },
                    modifier = Modifier.testTag("art_res_${res.label.lowercase()}")
                )
            }
        }

        Button(
            onClick = { onGenerateImage(prompt, selectedRes) },
            enabled = prompt.isNotBlank() && imageGenState !is ImageGenUiState.Loading,
            modifier = Modifier
                .fillMaxWidth()
                .defaultMinSize(minHeight = 52.dp)
                .testTag("generate_ai_art_button")
        ) {
            Icon(Icons.Filled.AutoAwesome, contentDescription = null)
            Spacer(modifier = Modifier.width(8.dp))
            Text("Create AI Picture (${selectedRes.label})")
        }

        ElevatedCard(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 240.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                when (imageGenState) {
                    is ImageGenUiState.Idle -> {
                        Text(
                            text = "Tap Create AI Picture to paint with Gemini!",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    is ImageGenUiState.Loading -> {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(10.dp),
                            modifier = Modifier.padding(vertical = 32.dp)
                        ) {
                            CircularProgressIndicator()
                            Text("Painting your magical artwork (may take 30–60s)...", style = MaterialTheme.typography.bodyMedium)
                        }
                    }
                    is ImageGenUiState.Success -> {
                        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            Text(
                                text = "Painted at ${imageGenState.resolution.label} ✨",
                                style = MaterialTheme.typography.labelLarge
                            )
                            Image(
                                bitmap = imageGenState.bitmap.asImageBitmap(),
                                contentDescription = "AI generated artwork: ${imageGenState.prompt}",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(MaterialTheme.shapes.medium),
                                contentScale = ContentScale.FillWidth
                            )
                        }
                    }
                    is ImageGenUiState.Error -> {
                        Text(
                            text = "Error: ${imageGenState.message}",
                            color = MaterialTheme.colorScheme.error,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
            }
        }
    }
}
