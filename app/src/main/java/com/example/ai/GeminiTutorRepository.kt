package com.example.ai

import com.example.data.model.ActivityType
import com.example.data.model.DifficultyLevel
import com.example.data.model.LearningActivity
import com.example.data.model.TutorModelTier
import com.example.data.model.WorldId
import com.google.firebase.Firebase
import com.google.firebase.ai.Chat
import com.google.firebase.ai.ai
import com.google.firebase.ai.type.Schema
import com.google.firebase.ai.type.content
import com.google.firebase.ai.type.generationConfig
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withTimeout
import org.json.JSONObject

class GeminiTutorRepository {

    private val systemPrompt = """
        You are Professor Hoot, a warm, cheerful, patient educational AI teacher for children aged 5 to 8.
        Rules:
        1. Always keep explanations super simple, encouraging, playful, and child-safe.
        2. Use short sentences and friendly emojis. Never scold a child for mistakes.
        3. If the child asks in Bengali or requests Bengali ("বাংলা"), explain clearly in simple child-friendly Bengali alongside any key English words.
        4. Help with English, Math, Bangla, Science, General Knowledge, Logic, Stories, Art, Memory, and Good Habits.
        5. Keep answers concise (3 to 5 short sentences) unless asked for a mini story.
    """.trimIndent()

    private val fastModel by lazy {
        Firebase.ai.generativeModel(
            modelName = TutorModelTier.FAST.modelName,
            systemInstruction = content { text(systemPrompt) }
        )
    }

    private val balancedModel by lazy {
        Firebase.ai.generativeModel(
            modelName = TutorModelTier.BALANCED.modelName,
            systemInstruction = content { text(systemPrompt) }
        )
    }

    private val deepModel by lazy {
        Firebase.ai.generativeModel(
            modelName = TutorModelTier.DEEP_REASONING.modelName,
            systemInstruction = content { text(systemPrompt) }
        )
    }

    private val practiceQuestionSchema = Schema.obj(
        properties = mapOf(
            "title" to Schema.string(description = "Short playful title for the question"),
            "topicTag" to Schema.string(description = "Short topic label like Phonics or Addition"),
            "prompt" to Schema.string(description = "Kid-friendly question prompt"),
            "promptBn" to Schema.string(description = "Simple Bengali translation or helper note"),
            "options" to Schema.array(
                items = Schema.string(),
                description = "Exactly 4 short answer choices"
            ),
            "correctAnswer" to Schema.string(description = "Must exactly match one item in options"),
            "hint" to Schema.string(description = "Gentle clue without giving away the answer"),
            "explanation" to Schema.string(description = "Cheerful 1-2 sentence explanation")
        )
    )

    private val practiceJsonModel by lazy {
        Firebase.ai.generativeModel(
            modelName = TutorModelTier.BALANCED.modelName,
            systemInstruction = content { text(systemPrompt) },
            generationConfig = generationConfig {
                responseMimeType = "application/json"
                responseSchema = practiceQuestionSchema
            }
        )
    }

    fun startChatSession(tier: TutorModelTier): Chat {
        val model = when (tier) {
            TutorModelTier.FAST -> fastModel
            TutorModelTier.BALANCED -> balancedModel
            TutorModelTier.DEEP_REASONING -> deepModel
        }
        return model.startChat(
            history = listOf(
                content("user") { text("Hi Professor Hoot! I am ready to learn.") },
                content("model") {
                    text("Hoo-hoo! Welcome young explorer! Ask me anything about English, Math, বাংলা, Science, or stories!")
                }
            )
        )
    }

    fun streamTutorReply(
        chat: Chat,
        userMessage: String,
        preferBengali: Boolean
    ): Flow<String> {
        val wrappedPrompt = if (preferBengali) {
            "$userMessage\n(Please explain primarily in simple, child-friendly Bengali script with helpful English words.)"
        } else {
            userMessage
        }
        return chat.sendMessageStream(wrappedPrompt).map { chunk -> chunk.text.orEmpty() }
    }

    suspend fun generatePracticeQuestion(
        worldId: WorldId,
        difficulty: DifficultyLevel
    ): LearningActivity {
        val prompt = """
            Create 1 brand-new interactive multiple-choice question for a 5-to-8-year-old child.
            Subject World: ${worldId.displayName} (${worldId.tagline}).
            Difficulty: ${difficulty.label}.
            Provide exactly 4 distinct options and ensure correctAnswer matches one option verbatim.
        """.trimIndent()

        val response = withTimeout(60_000L) {
            practiceJsonModel.generateContent(prompt)
        }
        val jsonText = response.text ?: throw IllegalStateException("Empty JSON returned by Gemini.")
        val obj = JSONObject(jsonText)
        val optionsJson = obj.getJSONArray("options")
        val options = (0 until optionsJson.length()).map { optionsJson.getString(it) }
        val correct = obj.getString("correctAnswer")
        val normalizedOptions = if (options.contains(correct)) options else (options.dropLast(1) + correct)

        return LearningActivity(
            id = "ai_${worldId.name.lowercase()}_${System.currentTimeMillis()}",
            worldId = worldId,
            title = obj.optString("title", "AI Bonus Challenge"),
            topicTag = obj.optString("topicTag", worldId.shortLabel),
            type = ActivityType.MULTIPLE_CHOICE,
            difficulty = difficulty,
            prompt = obj.getString("prompt"),
            promptBn = obj.optString("promptBn", ""),
            speechText = obj.getString("prompt"),
            options = normalizedOptions,
            correctAnswer = correct,
            hint = obj.optString("hint", "Take a deep breath and try your best!"),
            explanation = obj.optString("explanation", "Awesome thinking, explorer!"),
            visualEmoji = worldId.emoji
        )
    }

    fun offlineRuleBasedReply(userMessage: String, preferBengali: Boolean): String {
        val lower = userMessage.lowercase()
        return when {
            preferBengali -> when {
                "math" in lower || "যোগ" in lower || "+" in lower || "number" in lower ->
                    "হু-হু! অঙ্ক খুবই মজার! আঙুল বা ফল গুনে যোগ-বিয়োগ করলে খুব সহজেই উত্তর পাওয়া যায়। যেমন ২টি আপেল আর ৩টি আপেল মিলে হয় ৫টি আপেল!"
                "science" in lower || "planet" in lower || "বিজ্ঞান" in lower ->
                    "হু-হু! আমাদের সৌরজগতে সূর্য হলো মাঝখানের উজ্জ্বল নক্ষত্র, আর পৃথিবী তার চারপাশে ঘোরে! আমরা ৫টি ইন্দ্রিয় দিয়ে চারপাশ বুঝতে পারি।"
                else ->
                    "হু-হু! বর্তমানে অফলাইন টিচার মোড চালু আছে। তুমি খুব ভালো করছো—প্রতিদিন একটু একটু করে পড়া ও খেলা চালিয়ে যাও!"
            }
            "math" in lower || "add" in lower || "count" in lower ->
                "Hoo-hoo! Think of numbers like yummy apples! If you start at the bigger number and count forward step by step, addition becomes super easy!"
            "bangla" in lower || "বাংলা" in lower ->
                "Hoo-hoo! In Bangla, স্বরবর্ণ starts with অ, আ, ই, ঈ! Tap the Listen button in Bangla World to hear each letter slowly."
            "science" in lower || "space" in lower || "planet" in lower ->
                "Hoo-hoo! Science starts with curiosity! Our Earth orbits the Sun, and plants drink water through their roots to grow tall."
            else ->
                "Hoo-hoo! Offline Teacher Mode is active right now. Take your time, use the Listen and Hint buttons, and remember mistakes help our brain grow!"
        }
    }
}
