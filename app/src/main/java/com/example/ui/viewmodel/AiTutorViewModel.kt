package com.example.ui.viewmodel

import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ai.GeminiImageRepository
import com.example.ai.GeminiTutorRepository
import com.example.data.model.ArtResolutionOption
import com.example.data.model.DifficultyLevel
import com.example.data.model.LearningActivity
import com.example.data.model.TutorModelTier
import com.example.data.model.WorldId
import com.google.firebase.ai.Chat
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout

private const val TAG = "AiTutorViewModel"

data class TutorChatMessage(
    val id: String,
    val isFromUser: Boolean,
    val text: String,
    val isStreaming: Boolean = false
)

data class TutorUiState(
    val messages: List<TutorChatMessage> = listOf(
        TutorChatMessage(
            id = "welcome",
            isFromUser = false,
            text = "Hoo-hoo! I am Professor Hoot! Ask me any question in English or বাংলা, and I will explain it step by step!"
        )
    ),
    val selectedTier: TutorModelTier = TutorModelTier.BALANCED,
    val isGenerating: Boolean = false,
    val errorMessage: String? = null
)

sealed interface PracticeGenUiState {
    data object Idle : PracticeGenUiState
    data object Loading : PracticeGenUiState
    data class Success(val activity: LearningActivity) : PracticeGenUiState
    data class Error(val message: String) : PracticeGenUiState
}

sealed interface ImageGenUiState {
    data object Idle : ImageGenUiState
    data object Loading : ImageGenUiState
    data class Success(val bitmap: Bitmap, val prompt: String, val resolution: ArtResolutionOption) : ImageGenUiState
    data class Error(val message: String) : ImageGenUiState
}

class AiTutorViewModel : ViewModel() {

    private val tutorRepository = GeminiTutorRepository()
    private val imageRepository = GeminiImageRepository()

    private val _tutorState = MutableStateFlow(TutorUiState())
    val tutorState: StateFlow<TutorUiState> = _tutorState.asStateFlow()

    private val _practiceState = MutableStateFlow<PracticeGenUiState>(PracticeGenUiState.Idle)
    val practiceState: StateFlow<PracticeGenUiState> = _practiceState.asStateFlow()

    private val _imageGenState = MutableStateFlow<ImageGenUiState>(ImageGenUiState.Idle)
    val imageGenState: StateFlow<ImageGenUiState> = _imageGenState.asStateFlow()

    private var activeChatTier: TutorModelTier? = null
    private var activeChat: Chat? = null

    fun selectModelTier(tier: TutorModelTier) {
        if (_tutorState.value.selectedTier != tier) {
            activeChat = null
            activeChatTier = null
            _tutorState.update { it.copy(selectedTier = tier, errorMessage = null) }
        }
    }

    fun sendMessage(
        prompt: String,
        aiEnabledByParent: Boolean,
        preferBengali: Boolean
    ) {
        val cleanPrompt = prompt.trim()
        if (cleanPrompt.isEmpty() || _tutorState.value.isGenerating) return

        val userMsg = TutorChatMessage(
            id = "u_${System.currentTimeMillis()}",
            isFromUser = true,
            text = cleanPrompt
        )
        val assistantMsgId = "m_${System.currentTimeMillis() + 1}"

        if (!aiEnabledByParent) {
            val offlineReply = tutorRepository.offlineRuleBasedReply(cleanPrompt, preferBengali)
            _tutorState.update { state ->
                state.copy(
                    messages = state.messages + userMsg + TutorChatMessage(
                        id = assistantMsgId,
                        isFromUser = false,
                        text = offlineReply
                    ),
                    errorMessage = null
                )
            }
            return
        }

        _tutorState.update { state ->
            state.copy(
                messages = state.messages + userMsg + TutorChatMessage(
                    id = assistantMsgId,
                    isFromUser = false,
                    text = "",
                    isStreaming = true
                ),
                isGenerating = true,
                errorMessage = null
            )
        }

        viewModelScope.launch {
            val buffer = StringBuilder()
            try {
                val tier = _tutorState.value.selectedTier
                val chat = activeChat?.takeIf { activeChatTier == tier }
                    ?: tutorRepository.startChatSession(tier).also {
                        activeChat = it
                        activeChatTier = tier
                    }

                withTimeout(60_000L) {
                    tutorRepository.streamTutorReply(chat, cleanPrompt, preferBengali).collect { chunk ->
                        buffer.append(chunk)
                        val partial = buffer.toString()
                        _tutorState.update { state ->
                            state.copy(
                                messages = state.messages.map { msg ->
                                    if (msg.id == assistantMsgId) msg.copy(text = partial) else msg
                                }
                            )
                        }
                    }
                }

                val finalReply = buffer.toString().ifBlank {
                    throw IllegalStateException("Professor Hoot returned an empty reply.")
                }
                _tutorState.update { state ->
                    state.copy(
                        messages = state.messages.map { msg ->
                            if (msg.id == assistantMsgId) msg.copy(text = finalReply, isStreaming = false) else msg
                        },
                        isGenerating = false
                    )
                }
            } catch (e: TimeoutCancellationException) {
                Log.e(TAG, "Tutor streaming timed out", e)
                failStreamingMessage(assistantMsgId, "Connection timed out after 60 seconds. Please try again.")
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                Log.e(TAG, "Tutor streaming failed", e)
                failStreamingMessage(assistantMsgId, e.localizedMessage ?: "Professor Hoot could not connect right now.")
            }
        }
    }

    private fun failStreamingMessage(assistantMsgId: String, errorMsg: String) {
        _tutorState.update { state ->
            state.copy(
                messages = state.messages.filterNot { it.id == assistantMsgId && it.text.isBlank() }
                    .map { if (it.id == assistantMsgId) it.copy(isStreaming = false) else it },
                isGenerating = false,
                errorMessage = errorMsg
            )
        }
    }

    fun generatePracticeQuestion(
        worldId: WorldId,
        difficulty: DifficultyLevel,
        aiEnabledByParent: Boolean
    ) {
        if (!aiEnabledByParent) {
            _practiceState.value = PracticeGenUiState.Error(
                "AI Bonus Questions are paused in Parent Settings."
            )
            return
        }

        _practiceState.value = PracticeGenUiState.Loading
        viewModelScope.launch {
            try {
                val activity = tutorRepository.generatePracticeQuestion(worldId, difficulty)
                _practiceState.value = PracticeGenUiState.Success(activity)
            } catch (e: TimeoutCancellationException) {
                Log.e(TAG, "Practice generation timed out", e)
                _practiceState.value = PracticeGenUiState.Error("Timed out generating bonus question. Please try again.")
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                Log.e(TAG, "Practice generation failed", e)
                _practiceState.value = PracticeGenUiState.Error(
                    e.localizedMessage ?: "Failed to create AI bonus question."
                )
            }
        }
    }

    fun clearPracticeState() {
        _practiceState.value = PracticeGenUiState.Idle
    }

    fun generateArtImage(
        prompt: String,
        resolution: ArtResolutionOption,
        aiEnabledByParent: Boolean
    ) {
        val clean = prompt.trim()
        if (clean.isEmpty()) return
        if (!aiEnabledByParent) {
            _imageGenState.value = ImageGenUiState.Error(
                "AI Image Studio is currently turned off in Parent Settings."
            )
            return
        }

        _imageGenState.value = ImageGenUiState.Loading
        viewModelScope.launch {
            try {
                val bitmap = imageRepository.generateKidArtwork(clean, resolution)
                _imageGenState.value = ImageGenUiState.Success(bitmap, clean, resolution)
            } catch (e: TimeoutCancellationException) {
                Log.e(TAG, "Image generation timed out", e)
                _imageGenState.value = ImageGenUiState.Error("Image studio timed out after 60 seconds. Try again!")
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                Log.e(TAG, "Image generation failed", e)
                _imageGenState.value = ImageGenUiState.Error(
                    e.localizedMessage ?: "Image generation failed."
                )
            }
        }
    }
}
