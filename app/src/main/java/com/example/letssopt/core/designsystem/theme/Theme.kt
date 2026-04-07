package com.example.letssopt.core.designsystem.theme

import androidx.activity.compose.LocalActivity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

val LocalLETSSOPTColors = staticCompositionLocalOf { defaultLETSSOPTColors }

val LocalLETSSOPTTypography = staticCompositionLocalOf { defaultLETSSOPTTypography }

private val DarkColorScheme = darkColorScheme(
    primary = PrimaryRed,
    onPrimary = TextPrimary,
    background = Background,
    onBackground = TextPrimary,
    surface = Surface,
    onSurface = TextPrimary,
)

object LETSSOPTTheme {
    val colors: LETSSOPTColors
        @Composable
        @ReadOnlyComposable
        get() = LocalLETSSOPTColors.current

    val typography: LETSSOPTTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalLETSSOPTTypography.current
}

@Composable
fun ProvideLETSSOPTColorsAndTypography(
    colors: LETSSOPTColors,
    typography: LETSSOPTTypography,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalLETSSOPTColors provides colors,
        LocalLETSSOPTTypography provides typography,
        content = content
    )
}

@Composable
fun LETSSOPTTheme(
    content: @Composable () -> Unit
) {
    val view = LocalView.current
    val activity = LocalActivity.current

    if (!view.isInEditMode) {
        SideEffect {
            activity?.window?.let { window ->
                WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = false
            }
        }
    }

    ProvideLETSSOPTColorsAndTypography(
        colors = defaultLETSSOPTColors,
        typography = defaultLETSSOPTTypography
    ) {
        MaterialTheme(
            colorScheme = DarkColorScheme,
            content = content
        )
    }
}