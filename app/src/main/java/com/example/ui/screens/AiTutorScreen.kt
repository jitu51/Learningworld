package com.example.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.R
import com.example.data.model.TutorModelTier
import com.example.engine.AudioVoiceManager
import com.example.ui.viewmodel.TutorUiState

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun AiTutorScreen(
    initialContext: String,
    tutorState: TutorUiState,
    aiEnabledByParent: Boolean,
    preferBengali: Boolean,
    audioVoiceManager: AudioVoiceManager,
    onToggleLanguage: (String) -> Unit,
    onSelectModelTier: (TutorModelTier) -> Unit,
    onSendMessage: (String) -> Unit,
    onBack: () -> Unit
) {
    var inputText by remember(initialContext) { mutableStateOf(initialContext) }
    val listState = rememberLazyListState()

    LaunchedEffect(tutorState.messages.size) {
        if (tutorState.messages.isNotEmpty()) {
            listState.animateScrollToItem(tutorState.messages.lastIndex)
        }
    }

    val quickSuggestions = if (preferBengali) {
        listOf(
            "সহজ করে যোগ শেখাও",
            "সৌরজগতের গ্রহ কী কী?",
            "বাংলা স্বরবর্ণ শোনাও",
            "একটি ছোট নীতিগল্প বলো"
        )
    } else {
        listOf(
            "Explain addition with apples!",
            "Tell me about Saturn's rings",
            "How do I spell 'Butterfly'?",
            "Explain in simple Bengali"
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        Image(
                            painter = painterResource(id = R.drawable.img_mascot_tutor_1795539036610),
                            contentDescription = "Professor Hoot Owl Mascot",
                            modifier = Modifier
                                .size(36.dp)
                                .clip(CircleShape),
                            contentScale = ContentScale.Crop
                        )
                        Text(stringResource(R.string.ai_tutor_title), maxLines = 1)
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBack, modifier = Modifier.testTag("tutor_back_button")) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    FilterChip(
                        selected = preferBengali,
                        onClick = { onToggleLanguage(if (preferBengali) "EN" else "BN") },
                        label = { Text(if (preferBengali) "বাংলা" else "EN") },
                        modifier = Modifier
                            .padding(end = 8.dp)
                            .testTag("tutor_language_toggle")
                    )
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .imePadding()
        ) {
            if (!aiEnabledByParent) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 6.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
                ) {
                    Text(
                        text = stringResource(R.string.ai_disabled_by_parent),
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(12.dp)
                    )
                }
            }

            // Tier Selector Row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 4.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                TutorModelTier.entries.forEach { tier ->
                    FilterChip(
                        selected = tutorState.selectedTier == tier,
                        onClick = { onSelectModelTier(tier) },
                        label = { Text(tier.label) },
                        modifier = Modifier.testTag("tutor_tier_${tier.name.lowercase()}")
                    )
                }
            }

            LazyColumn(
                state = listState,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(tutorState.messages, key = { it.id }) { msg ->
                    val align = if (msg.isFromUser) Alignment.End else Alignment.Start
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = align
                    ) {
                        Surface(
                            shape = MaterialTheme.shapes.medium,
                            color = if (msg.isFromUser) {
                                MaterialTheme.colorScheme.primary
                            } else {
                                MaterialTheme.colorScheme.secondaryContainer
                            },
                            modifier = Modifier.widthIn(max = 310.dp)
                        ) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                Text(
                                    text = if (msg.text.isEmpty() && msg.isStreaming) "Thinking..." else msg.text,
                                    color = if (msg.isFromUser) {
                                        MaterialTheme.colorScheme.onPrimary
                                    } else {
                                        MaterialTheme.colorScheme.onSecondaryContainer
                                    },
                                    style = MaterialTheme.typography.bodyLarge
                                )
                                if (!msg.isFromUser && msg.text.isNotBlank() && !msg.isStreaming) {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.End
                                    ) {
                                        IconButton(
                                            onClick = { audioVoiceManager.speak(msg.text, isBengali = preferBengali) },
                                            modifier = Modifier.size(34.dp)
                                        ) {
                                            Icon(
                                                imageVector = Icons.AutoMirrored.Filled.VolumeUp,
                                                contentDescription = "Read reply aloud",
                                                modifier = Modifier.size(18.dp)
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                if (tutorState.errorMessage != null) {
                    item {
                        Card(
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.error.copy(alpha = 0.14f))
                        ) {
                            Text(
                                text = tutorState.errorMessage,
                                color = MaterialTheme.colorScheme.error,
                                style = MaterialTheme.typography.bodyMedium,
                                modifier = Modifier.padding(12.dp)
                            )
                        }
                    }
                }
            }

            // Quick topic chips
            FlowRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 4.dp),
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                quickSuggestions.forEachIndexed { idx, prompt ->
                    AssistChip(
                        onClick = {
                            onSendMessage(prompt)
                        },
                        label = { Text(prompt, maxLines = 1) },
                        modifier = Modifier.testTag("tutor_chip_$idx")
                    )
                }
            }

            // Input Row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedTextField(
                    value = inputText,
                    onValueChange = { inputText = it },
                    placeholder = { Text(if (preferBengali) "হুট স্যারকে প্রশ্ন করো..." else "Ask Professor Hoot...") },
                    modifier = Modifier
                        .weight(1f)
                        .testTag("tutor_input_field"),
                    maxLines = 3
                )
                FilledIconButton(
                    onClick = {
                        val toSend = inputText
                        inputText = ""
                        onSendMessage(toSend)
                    },
                    enabled = inputText.isNotBlank() && !tutorState.isGenerating,
                    modifier = Modifier
                        .size(52.dp)
                        .testTag("tutor_send_button")
                ) {
                    Icon(Icons.AutoMirrored.Filled.Send, contentDescription = "Send question")
                }
            }
        }
    }
}
