package com.example.data.content

import com.example.data.model.*

object ContentRepository {
    val allActivities: List<LearningActivity> by lazy {
        ContentCatalogPart1.englishActivities +
            ContentCatalogPart1.mathActivities +
            ContentCatalogPart2.banglaActivities +
            ContentCatalogPart2.scienceActivities +
            ContentCatalogPart3.gkActivities +
            ContentCatalogPart3.logicActivities +
            StoryCatalog.storyActivities +
            ContentCatalogPart4.artActivities +
            ContentCatalogPart4.memoryActivities +
            ContentCatalogPart4.realLifeActivities
    }

    val allStories: List<InteractiveStory> = StoryCatalog.stories
    val mapLandmarks: List<MapLandmark> = ContentCatalogPart3.mapLandmarks
    val allBadges: List<BadgeDef> = ContentCatalogPart4.badges
    val allCharacters: List<UnlockableCharacter> = ContentCatalogPart4.unlockableCharacters

    private val activitiesById: Map<String, LearningActivity> by lazy {
        allActivities.associateBy { it.id }
    }

    fun activitiesForWorld(worldId: WorldId): List<LearningActivity> =
        allActivities.filter { it.worldId == worldId }

    fun activityById(id: String): LearningActivity? = activitiesById[id]

    fun dailyChallengeForDate(dateKey: String): LearningActivity {
        val index = (dateKey.hashCode().let { if (it < 0) -it else it }) % allActivities.size
        return allActivities[index]
    }
}
