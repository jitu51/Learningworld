package com.example.engine

import android.content.Context
import android.content.Intent
import android.media.AudioAttributes
import android.media.AudioFormat
import android.media.AudioTrack
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.speech.tts.TextToSpeech
import android.util.Log
import kotlinx.coroutines.*
import java.util.Locale
import kotlin.math.PI
import kotlin.math.sin

class AudioVoiceManager(private val context: Context) {

    private var tts: TextToSpeech? = null
    private var ttsReady = false
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Default)
    private var musicJob: Job? = null

    var soundEffectsEnabled: Boolean = true
    var voiceReadAloudEnabled: Boolean = true

    init {
        try {
            tts = TextToSpeech(context.applicationContext) { status ->
                if (status == TextToSpeech.SUCCESS) {
                    ttsReady = true
                    tts?.setSpeechRate(0.85f)
                    tts?.setPitch(1.08f)
                }
            }
        } catch (e: Exception) {
            Log.w("AudioVoiceManager", "TTS unavailable", e)
        }
    }

    fun speak(text: String, isBengali: Boolean = false) {
        if (!voiceReadAloudEnabled || !ttsReady || text.isBlank()) return
        try {
            val targetLocale = if (isBengali) Locale("bn", "BD") else Locale.US
            val support = tts?.isLanguageAvailable(targetLocale) ?: TextToSpeech.LANG_NOT_SUPPORTED
            if (support >= TextToSpeech.LANG_AVAILABLE) {
                tts?.language = targetLocale
            } else {
                tts?.language = Locale.US
            }
            tts?.setSpeechRate(0.85f)
            tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null, "klw_${System.currentTimeMillis()}")
        } catch (e: Exception) {
            Log.w("AudioVoiceManager", "Speak failed", e)
        }
    }

    fun stopSpeaking() {
        try {
            tts?.stop()
        } catch (_: Exception) {}
    }

    fun playSuccessChime() {
        if (!soundEffectsEnabled) return
        scope.launch {
            playTone(523.25, 110) // C5
            delay(15)
            playTone(659.25, 110) // E5
            delay(15)
            playTone(783.99, 170) // G5
        }
    }

    fun playGentleTryAgain() {
        if (!soundEffectsEnabled) return
        scope.launch {
            playTone(392.0, 120)
            delay(20)
            playTone(349.23, 150)
        }
    }

    fun setBackgroundMusic(enabled: Boolean) {
        musicJob?.cancel()
        if (!enabled) return
        musicJob = scope.launch {
            val lullabyNotes = listOf(261.63, 329.63, 392.00, 329.63, 293.66, 349.23, 392.00, 261.63)
            var index = 0
            while (isActive) {
                playTone(lullabyNotes[index % lullabyNotes.size], 320, volume = 0.08f)
                delay(180)
                index++
            }
        }
    }

    private fun playTone(freqHz: Double, durationMs: Int, volume: Float = 0.22f) {
        try {
            val sampleRate = 22050
            val numSamples = (sampleRate * durationMs) / 1000
            val samples = ShortArray(numSamples)
            for (i in 0 until numSamples) {
                val env = 1.0 - (i.toDouble() / numSamples)
                val angle = 2.0 * PI * i * freqHz / sampleRate
                samples[i] = (sin(angle) * Short.MAX_VALUE * volume * env).toInt().toShort()
            }
            val bufferSize = samples.size * 2
            val track = AudioTrack.Builder()
                .setAudioAttributes(
                    AudioAttributes.Builder()
                        .setUsage(AudioAttributes.USAGE_GAME)
                        .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                        .build()
                )
                .setAudioFormat(
                    AudioFormat.Builder()
                        .setEncoding(AudioFormat.ENCODING_PCM_16BIT)
                        .setSampleRate(sampleRate)
                        .setChannelMask(AudioFormat.CHANNEL_OUT_MONO)
                        .build()
                )
                .setTransferMode(AudioTrack.MODE_STATIC)
                .setBufferSizeInBytes(bufferSize)
                .build()

            track.write(samples, 0, samples.size)
            track.play()
            Thread.sleep(durationMs.toLong() + 30L)
            track.stop()
            track.release()
        } catch (_: Exception) {
            // Audio device unavailable in headless/test runtime; safely ignored
        }
    }

    fun startSpeechCapture(
        isBengali: Boolean,
        onResult: (String) -> Unit,
        onError: (String) -> Unit
    ): SpeechRecognizer? {
        if (!SpeechRecognizer.isRecognitionAvailable(context)) {
            onError("Voice recognition is unavailable on this device. You can type your answer below!")
            return null
        }
        return try {
            val recognizer = SpeechRecognizer.createSpeechRecognizer(context)
            recognizer.setRecognitionListener(object : RecognitionListener {
                override fun onReadyForSpeech(params: Bundle?) {}
                override fun onBeginningOfSpeech() {}
                override fun onRmsChanged(rmsdB: Float) {}
                override fun onBufferReceived(buffer: ByteArray?) {}
                override fun onEndOfSpeech() {}
                override fun onPartialResults(partialResults: Bundle?) {}
                override fun onEvent(eventType: Int, params: Bundle?) {}

                override fun onError(error: Int) {
                    onError("Could not hear clearly. Try again or type your answer!")
                    recognizer.destroy()
                }

                override fun onResults(results: Bundle?) {
                    val matches = results?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
                    val heard = matches?.firstOrNull().orEmpty()
                    if (heard.isNotBlank()) onResult(heard) else onError("No speech detected.")
                    recognizer.destroy()
                }
            })
            val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
                putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
                putExtra(RecognizerIntent.EXTRA_LANGUAGE, if (isBengali) "bn-BD" else "en-US")
            }
            recognizer.startListening(intent)
            recognizer
        } catch (e: Exception) {
            onError("Voice input unavailable. Please type your answer below!")
            null
        }
    }

    fun release() {
        musicJob?.cancel()
        scope.cancel()
        try {
            tts?.stop()
            tts?.shutdown()
        } catch (_: Exception) {}
    }
}
