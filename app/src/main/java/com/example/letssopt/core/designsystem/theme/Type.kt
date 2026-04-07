package com.example.letssopt.core.designsystem.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.sp
import com.example.letssopt.R

object PretendardFont {
    val bold = FontFamily(Font(R.font.pretendard_bold))
    val regular = FontFamily(Font(R.font.pretendard_regular))
}

private object TypographyDefaults {
    val platformStyle = PlatformTextStyle(
        includeFontPadding = false,
    )
    val lineHeightStyle = LineHeightStyle(
        alignment = LineHeightStyle.Alignment.Center,
        trim = LineHeightStyle.Trim.None,
    )
}

sealed interface TypographyTokens {
    @Immutable
    data class Bold(
        val l1: TextStyle, // 로고 제목 L1
        val h1: TextStyle, // 제목 H1
        val h2: TextStyle, // 제목 H2
        val button: TextStyle // 버튼 button
    )

    @Immutable
    data class Regular(
        val body: TextStyle, // 본문 body
        val caption: TextStyle, // 캡션 caption
    )
}

@Immutable
data class LETSSOPTTypography(
    val bold: TypographyTokens.Bold,
    val regular: TypographyTokens.Regular,
)

val defaultLETSSOPTTypography = LETSSOPTTypography(
    bold = TypographyTokens.Bold(
        l1 = TextStyle(
            fontFamily = PretendardFont.bold,
            fontWeight = FontWeight.Bold,
            fontSize = 36.sp,
            lineHeight = 36.sp,
            letterSpacing = 0.sp,
            platformStyle = TypographyDefaults.platformStyle,
            lineHeightStyle = TypographyDefaults.lineHeightStyle
        ),
        h1 = TextStyle(
            fontFamily = PretendardFont.bold,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.sp,
            platformStyle = TypographyDefaults.platformStyle,
            lineHeightStyle = TypographyDefaults.lineHeightStyle
        ),
        h2 = TextStyle(
            fontFamily = PretendardFont.bold,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            lineHeight = 20.sp,
            letterSpacing = 0.sp,
            platformStyle = TypographyDefaults.platformStyle,
            lineHeightStyle = TypographyDefaults.lineHeightStyle
        ),
        button = TextStyle(
            fontFamily = PretendardFont.bold,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            lineHeight = 16.sp,
            letterSpacing = 0.sp,
            platformStyle = TypographyDefaults.platformStyle,
            lineHeightStyle = TypographyDefaults.lineHeightStyle
        )
    ),
    regular = TypographyTokens.Regular(
        body = TextStyle(
            fontFamily = PretendardFont.regular,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            lineHeight = 26.sp,
            letterSpacing = 0.sp,
            platformStyle = TypographyDefaults.platformStyle,
            lineHeightStyle = TypographyDefaults.lineHeightStyle
        ),
        caption = TextStyle(
            fontFamily = PretendardFont.regular,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            lineHeight = 14.sp,
            letterSpacing = 0.sp,
            platformStyle = TypographyDefaults.platformStyle,
            lineHeightStyle = TypographyDefaults.lineHeightStyle
        ),
    )
)