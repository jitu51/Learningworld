package com.example.ai

import android.graphics.Bitmap
import com.example.data.model.ArtResolutionOption
import com.google.firebase.Firebase
import com.google.firebase.ai.ai
import com.google.firebase.ai.type.AspectRatio
import com.google.firebase.ai.type.ImageConfig
import com.google.firebase.ai.type.ImagePart
import com.google.firebase.ai.type.ImageSize
import com.google.firebase.ai.type.ResponseModality
import com.google.firebase.ai.type.generationConfig
import kotlinx.coroutines.withTimeout

class GeminiImageRepository {

    private fun buildModel(resolution: ArtResolutionOption) = Firebase.ai.generativeModel(
        modelName = "gemini-3-pro-image",
        generationConfig = generationConfig {
            responseModalities = listOf(ResponseModality.IMAGE)
            imageConfig = ImageConfig.builder()
                .setAspectRatio(AspectRatio.SQUARE_1x1)
                .setImageSize(
                    when (resolution) {
                        ArtResolutionOption.RES_1K -> ImageSize.SIZE_1K
                        ArtResolutionOption.RES_2K -> ImageSize.SIZE_2K
                        ArtResolutionOption.RES_4K -> ImageSize.SIZE_4K
                    }
                )
                .build()
        }
    )

    suspend fun generateKidArtwork(
        prompt: String,
        resolution: ArtResolutionOption
    ): Bitmap {
        val safePrompt = """
            Child-friendly colorful storybook illustration for ages 5-8, cheerful rounded cartoon style,
            bright warm palette, zero scary elements, no text overlays: $prompt
        """.trimIndent()

        val model = buildModel(resolution)
        val response = withTimeout(60_000L) {
            model.generateContent(safePrompt)
        }

        val imagePart = response.candidates.firstOrNull()
            ?.content?.parts?.filterIsInstance<ImagePart>()
            ?.firstOrNull()

        return imagePart?.image
            ?: throw IllegalStateException("Gemini did not return an image. Please try another fun idea!")
    }
}
