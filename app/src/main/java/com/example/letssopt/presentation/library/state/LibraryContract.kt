package com.example.letssopt.presentation.library.state

import androidx.compose.runtime.Immutable
import com.example.letssopt.presentation.library.model.LibraryUiModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Immutable
data class LibraryUiState(
    val items: ImmutableList<LibraryUiModel> = persistentListOf()
)