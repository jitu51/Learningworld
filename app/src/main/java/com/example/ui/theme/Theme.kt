package com.example.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

private val LightStorybookColors = lightColorScheme(
    primary = SkyCerulean,
    onPrimary = CloudWhite,
    primaryContainer = SoftSkyContainer,
    onPrimaryContainer = StorybookIndigo,
    secondary = SunburstAmber,
    onSecondary = SlateInk,
    secondaryContainer = SoftPeachContainer,
    onSecondaryContainer = SlateInk,
    tertiary = MeadowGreen,
    onTertiary = CloudWhite,
    tertiaryContainer = SoftMintContainer,
    onTertiaryContainer = SlateInk,
    background = CreamCanvas,
    onBackground = SlateInk,
    surface = CloudWhite,
    onSurface = SlateInk,
    surfaceVariant = SoftLavenderContainer,
    onSurfaceVariant = MutedSlate,
    outline = SoftOutline,
    error = BerryCoral,
    onError = CloudWhite
)

private val DarkStorybookColors = darkColorScheme(
    primary = TwilightTeal,
    onPrimary = TwilightCanvas,
    primaryContainer = TwilightElevated,
    onPrimaryContainer = TwilightInk,
    secondary = TwilightAmber,
    onSecondary = TwilightCanvas,
    secondaryContainer = TwilightElevated,
    onSecondaryContainer = TwilightInk,
    tertiary = TwilightCoral,
    onTertiary = TwilightCanvas,
    tertiaryContainer = TwilightSurface,
    onTertiaryContainer = TwilightInk,
    background = TwilightCanvas,
    onBackground = TwilightInk,
    surface = TwilightSurface,
    onSurface = TwilightInk,
    surfaceVariant = TwilightElevated,
    onSurfaceVariant = TwilightMuted,
    outline = TwilightElevated,
    error = TwilightCoral,
    onError = TwilightCanvas
)

val StorybookShapes = Shapes(
    small = RoundedCornerShape(14.dp),
    medium = RoundedCornerShape(20.dp),
    large = RoundedCornerShape(26.dp),
    extraLarge = RoundedCornerShape(32.dp)
)

@Composable
fun KidsLearningWorldTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkStorybookColors else LightStorybookColors

    MaterialTheme(
        colorScheme = colorScheme,
        typography = AppTypography,
        shapes = StorybookShapes,
        content = content
    )
}
