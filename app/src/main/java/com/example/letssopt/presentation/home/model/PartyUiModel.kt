package com.example.letssopt.presentation.home.model

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Immutable

@Immutable
data class PartyUiModel(
    @param:DrawableRes val imageRes: Int,
    val startTime: String,
    val tag: String
)