package com.example.data.model

import androidx.compose.ui.graphics.Color
import com.example.ui.theme.*

enum class WorldId(
    val displayName: String,
    val tagline: String,
    val emoji: String,
    val accentColor: Color,
    val shortLabel: String
) {
    ENGLISH("English Language", "Letters, phonics & stories", "🔤", WorldEnglishColor, "English"),
    MATH("Mathematics", "Numbers, puzzles & shapes", "🔢", WorldMathColor, "Math"),
    BANGLA("Bangla Language", "বাংলা বর্ণমালা ও শব্দ", "🇧🇩", WorldBanglaColor, "Bangla"),
    SCIENCE("Science Discovery", "Space, senses & nature", "🔬", WorldScienceColor, "Science"),
    GK("General Knowledge", "Flags, maps & world", "🌍", WorldGkColor, "World GK"),
    LOGIC("Logic & Brain", "Sudoku, patterns & riddles", "🧩", WorldLogicColor, "Logic"),
    STORY("Story Adventure", "Interactive choices & tales", "📖", WorldStoryColor, "Stories"),
    ART("Art & Creativity", "Color, paint & AI Art", "🎨", WorldArtColor, "Art"),
    MEMORY("Memory & Attention", "Cards, sequences & focus", "🧠", WorldMemoryColor, "Memory"),
    REAL_LIFE("Real-Life Learning", "Safety, habits & kindness", "🌟", WorldRealLifeColor, "Life Skills")
}

enum class DifficultyLevel(val label: String) {
    BEGINNER("Beginner"),
    EXPLORER("Explorer"),
    MASTER("Master")
}

enum class ActivityType(val label: String) {
    MULTIPLE_CHOICE("Quiz Choice"),
    DRAG_SORT("Drag & Sort"),
    PICTURE_MATCH("Picture Match"),
    AUDIO_LISTEN("Listen & Pick"),
    FILL_BLANK("Fill the Blank"),
    WORD_BUILD("Word Builder"),
    PAIR_MATCH("Pair Matching"),
    MEMORY_CARDS("Memory Cards"),
    SUDOKU_4X4("Kids Sudoku 4x4"),
    SEQUENCE_RECALL("Sequence Recall"),
    SPOT_DIFFERENCE("Spot & Find"),
    VOICE_SPEAK("Speak & Repeat"),
    ANIMATED_SCENE("Interactive Scene"),
    INTERACTIVE_STORY("Story Choice")
}

data class LearningActivity(
    val id: String,
    val worldId: WorldId,
    val title: String,
    val topicTag: String,
    val type: ActivityType,
    val difficulty: DifficultyLevel,
    val prompt: String,
    val promptBn: String = "",
    val speechText: String = prompt,
    val options: List<String> = emptyList(),
    val correctAnswer: String = "",
    val hint: String = "Think carefully and take your time!",
    val explanation: String = "Great job exploring!",
    val explanationBn: String = "দারুণ করেছো!",
    val visualEmoji: String = "✨",
    val isVocabulary: Boolean = false,
    val dragItems: List<Pair<String, String>> = emptyList(),
    val buckets: List<String> = emptyList(),
    val matchPairs: List<Pair<String, String>> = emptyList(),
    val memoryPairs: List<Pair<String, String>> = emptyList(),
    val sequenceItems: List<String> = emptyList(),
    val sudokuGrid: List<Int> = emptyList(),
    val sudokuSolution: List<Int> = emptyList(),
    val differenceTargets: List<String> = emptyList()
)

data class StoryChoice(
    val text: String,
    val nextNodeId: String,
    val pointsBonus: Int = 5
)

data class StoryNode(
    val id: String,
    val sceneEmoji: String,
    val title: String,
    val narration: String,
    val vocabularyWord: String = "",
    val vocabularyMeaning: String = "",
    val choices: List<StoryChoice> = emptyList(),
    val comprehensionQuestion: String = "",
    val comprehensionOptions: List<String> = emptyList(),
    val comprehensionAnswer: String = "",
    val moralLesson: String = ""
)

data class InteractiveStory(
    val id: String,
    val title: String,
    val heroName: String,
    val heroEmoji: String,
    val summary: String,
    val startNodeId: String,
    val nodes: Map<String, StoryNode>
)

data class MapLandmark(
    val id: String,
    val name: String,
    val country: String,
    val continent: String,
    val capital: String,
    val flagEmoji: String,
    val funFact: String,
    val latitude: Double,
    val longitude: Double,
    val quizQuestion: String,
    val quizOptions: List<String>,
    val quizCorrectAnswer: String
)

data class BadgeDef(
    val id: String,
    val title: String,
    val description: String,
    val emoji: String,
    val requiredStars: Int = 0,
    val requiredWorld: WorldId? = null
)

data class UnlockableCharacter(
    val id: String,
    val name: String,
    val emoji: String,
    val description: String,
    val requiredPoints: Int
)

enum class ArtResolutionOption(val label: String, val pixelsHint: String) {
    RES_1K("1K", "1024px • Fast"),
    RES_2K("2K", "2048px • Crisp"),
    RES_4K("4K", "4096px • Ultra")
}

enum class TutorModelTier(val label: String, val modelName: String, val subtitle: String) {
    FAST("Quick", "gemini-3.5-flash-lite", "Fastest replies"),
    BALANCED("Standard", "gemini-3.8-flash", "Best everyday tutor"),
    DEEP_REASONING("Deep Think", "gemini-4-argon", "Step-by-step puzzles")
}
