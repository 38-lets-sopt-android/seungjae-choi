package com.example.letssopt.presentation.home.model

import androidx.annotation.DrawableRes

data class PartyUiModel(
    @param:DrawableRes val imageRes: Int,
    val startTime: String,
    val tag: String
)