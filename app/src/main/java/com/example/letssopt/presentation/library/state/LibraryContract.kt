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
            LibraryUiModel(1L, R.drawable.img_content1, "이 사람 통역 되나요"),
            LibraryUiModel(2L, R.drawable.img_content2, "이상한일5"),
            LibraryUiModel(3L, R.drawable.img_content3, "하일매리"),
            LibraryUiModel(4L, R.drawable.img_content1, "이 사람 통역 되나요"),
            LibraryUiModel(5L, R.drawable.img_content2, "이상한일5")
        )
    }
}

sealed interface LibrarySideEffect {
    data class ShowToast(val message: String) : LibrarySideEffect
}