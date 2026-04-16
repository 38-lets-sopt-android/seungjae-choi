package com.example.letssopt.presentation.library.state

import com.example.letssopt.presentation.library.model.LibraryUiModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

data class LibraryUiState(
    val items: ImmutableList<LibraryUiModel> = persistentListOf()
)