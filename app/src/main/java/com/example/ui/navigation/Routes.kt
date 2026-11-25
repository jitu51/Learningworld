package com.example.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
data object HomeRoute

@Serializable
data class WorldHubRoute(val worldIdName: String)

@Serializable
data class ActivityPlayRoute(
    val worldIdName: String,
    val activityId: String,
    val isDailyChallenge: Boolean = false
)

@Serializable
data class StoryAdventureRoute(val storyId: String? = null)

@Serializable
data object ArtStudioRoute

@Serializable
data object MapExplorerRoute

@Serializable
data object ProgressRoute

@Serializable
data object BadgesRoute

@Serializable
data object DailyPlanRoute

@Serializable
data class AiTutorRoute(val initialContext: String = "")

@Serializable
data object ParentDashboardRoute
