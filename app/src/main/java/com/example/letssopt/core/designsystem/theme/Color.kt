package com.example.letssopt.core.designsystem.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

val PrimaryRed = Color(0xFFE8003C)
val Background = Color(0xFF141414)
val Surface = Color(0xFF2A2A2A)
val TextPrimary = Color(0xFFFFFFFF)
val TextSecondary = Color(0xFF999999)
val Placeholder = Color(0xFF666666)
val Disabled = Color(0xFF333333)

@Immutable
data class LETSSOPTColors(
    val primaryRed: Color = PrimaryRed,
    val background: Color = Background,
    val surface: Color = Surface,
    val textPrimary: Color = TextPrimary,
    val textSecondary: Color = TextSecondary,
    val placeholder: Color = Placeholder,
    val disabled: Color = Disabled,
)

val defaultLETSSOPTColors = LETSSOPTColors()