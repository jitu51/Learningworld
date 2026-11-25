package com.example.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material.icons.filled.Replay
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
import com.example.data.model.InteractiveStory
import com.example.engine.AudioVoiceManager

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StoryAdventureScreen(
    initialStoryId: String?,
    audioVoiceManager: AudioVoiceManager,
    onStoryCompleted: (String) -> Unit,
    onBack: () -> Unit
) {
    val stories = ContentRepository.allStories
    var activeStory by remember(initialStoryId) {
        mutableStateOf(stories.firstOrNull { it.id == initialStoryId })
    }
    var currentNodeId by remember(activeStory?.id) {
        mutableStateOf(activeStory?.startNodeId ?: "start")
    }
    var quizFeedback by remember(activeStory?.id, currentNodeId) { mutableStateOf<String?>(null) }

    BackHandler(enabled = activeStory != null) {
        activeStory = null
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(activeStory?.title ?: "Story Adventure Library", maxLines = 1) },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            if (activeStory != null) activeStory = null else onBack()
                        },
                        modifier = Modifier.testTag("story_back_button")
                    ) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        val story = activeStory
        if (story == null) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                item {
                    Image(
                        painter = painterResource(id = R.drawable.img_story_forest_1795539036943),
                        contentDescription = "Storybook forest",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(170.dp)
                            .clip(MaterialTheme.shapes.large),
                        contentScale = ContentScale.Crop
                    )
                }
                item {
                    Text(
                        text = "Choose an Interactive Tale (${stories.size} Stories)",
                        style = MaterialTheme.typography.headlineMedium
                    )
                }
                itemsIndexed(stories) { idx, item ->
                    ElevatedCard(
                        onClick = {
                            activeStory = item
                            currentNodeId = item.startNodeId
                            quizFeedback = null
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("story_card_$idx")
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(14.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            Text(item.heroEmoji, fontSize = 36.sp)
                            Column(modifier = Modifier.weight(1f)) {
                                Text(item.title, style = MaterialTheme.typography.titleMedium)
                                Text(
                                    text = "${item.heroName} • ${item.summary}",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    }
                }
            }
        } else {
            StoryPlayerBody(
                story = story,
                nodeId = currentNodeId,
                quizFeedback = quizFeedback,
                audioVoiceManager = audioVoiceManager,
                onSelectChoice = { nextId ->
                    currentNodeId = nextId
                    quizFeedback = null
                },
                onAnswerQuiz = { option ->
                    val node = story.nodes[currentNodeId] ?: return@StoryPlayerBody
                    if (option == node.comprehensionAnswer) {
                        audioVoiceManager.playSuccessChime()
                        onStoryCompleted(story.id)
                        quizFeedback = "🎉 Awesome comprehension! +25 Points & Story Badge unlocked!"
                    } else {
                        audioVoiceManager.playGentleTryAgain()
                        quizFeedback = "💛 Think back to ${story.heroName}'s choice and try again!"
                    }
                },
                onReplay = {
                    currentNodeId = story.startNodeId
                    quizFeedback = null
                },
                onBackToLibrary = { activeStory = null },
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@Composable
private fun StoryPlayerBody(
    story: InteractiveStory,
    nodeId: String,
    quizFeedback: String?,
    audioVoiceManager: AudioVoiceManager,
    onSelectChoice: (String) -> Unit,
    onAnswerQuiz: (String) -> Unit,
    onReplay: () -> Unit,
    onBackToLibrary: () -> Unit,
    modifier: Modifier = Modifier
) {
    val node = story.nodes[nodeId] ?: story.nodes[story.startNodeId] ?: return

    LaunchedEffect(story.id, node.id) {
        audioVoiceManager.speak(node.narration)
    }

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        item {
            ElevatedCard(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
            ) {
                Column(modifier = Modifier.padding(18.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("${story.heroEmoji} ${node.sceneEmoji}", fontSize = 36.sp)
                        FilledTonalButton(
                            onClick = { audioVoiceManager.speak(node.narration) },
                            modifier = Modifier.testTag("story_read_aloud_button")
                        ) {
                            Icon(Icons.AutoMirrored.Filled.VolumeUp, contentDescription = "Narrate story")
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("Narrate")
                        }
                    }
                    Text(node.title, style = MaterialTheme.typography.headlineMedium)
                    Text(node.narration, style = MaterialTheme.typography.bodyLarge)
                }
            }
        }

        if (node.vocabularyWord.isNotBlank()) {
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Text("📚 Story Word: ${node.vocabularyWord}", style = MaterialTheme.typography.titleMedium)
                        Text(node.vocabularyMeaning, style = MaterialTheme.typography.bodyMedium)
                    }
                }
            }
        }

        if (node.choices.isNotEmpty()) {
            item { Text("What should ${story.heroName} do?", style = MaterialTheme.typography.titleMedium) }
            itemsIndexed(node.choices) { idx, choice ->
                Button(
                    onClick = { onSelectChoice(choice.nextNodeId) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .defaultMinSize(minHeight = 54.dp)
                        .testTag("story_choice_$idx")
                ) {
                    Text(choice.text)
                }
            }
        }

        if (node.comprehensionQuestion.isNotBlank()) {
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
                ) {
                    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                        Text("🌟 Moral Lesson: ${node.moralLesson}", style = MaterialTheme.typography.titleMedium)
                        HorizontalDivider()
                        Text(node.comprehensionQuestion, style = MaterialTheme.typography.titleMedium)
                        node.comprehensionOptions.forEachIndexed { idx, opt ->
                            OutlinedButton(
                                onClick = { onAnswerQuiz(opt) },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .defaultMinSize(minHeight = 48.dp)
                                    .testTag("story_quiz_opt_$idx")
                            ) {
                                Text(opt)
                            }
                        }
                        if (quizFeedback != null) {
                            Text(quizFeedback, style = MaterialTheme.typography.bodyLarge)
                        }
                    }
                }
            }

            item {
                Row(horizontalArrangement = Arrangement.spacedBy(10.dp), modifier = Modifier.fillMaxWidth()) {
                    OutlinedButton(onClick = onReplay, modifier = Modifier.weight(1f)) {
                        Icon(Icons.Filled.Replay, contentDescription = null)
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("Replay Story")
                    }
                    Button(onClick = onBackToLibrary, modifier = Modifier.weight(1f)) {
                        Text("More Stories")
                    }
                }
            }
        }
    }
}
