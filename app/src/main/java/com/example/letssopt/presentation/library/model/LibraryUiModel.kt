package com.example.letssopt.presentation.library.model

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Immutable

@Immutable
data class LibraryUiModel(
    val id: Long,
    @param:DrawableRes val imageRes: Int,
    val title: String
)