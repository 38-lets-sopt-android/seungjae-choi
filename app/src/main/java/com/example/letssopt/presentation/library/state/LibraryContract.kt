package com.example.letssopt.presentation.library.state

import androidx.compose.runtime.Immutable
import com.example.letssopt.R
import com.example.letssopt.presentation.library.model.LibraryUiModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Immutable
data class LibraryUiState(
    val items: ImmutableList<LibraryUiModel> = persistentListOf()
) {
    companion object {
        val dummyItems = listOf(
            LibraryUiModel(1, R.drawable.img_content1),
            LibraryUiModel(2, R.drawable.img_content2),
            LibraryUiModel(3, R.drawable.img_content3),
            LibraryUiModel(4, R.drawable.img_content1),
            LibraryUiModel(5, R.drawable.img_content2),
            LibraryUiModel(6, R.drawable.img_content3),
            LibraryUiModel(7, R.drawable.img_content1),
        )
    }
}