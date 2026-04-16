package com.example.letssopt.presentation.library

import androidx.lifecycle.ViewModel
import com.example.letssopt.R
import com.example.letssopt.presentation.library.model.LibraryUiModel
import com.example.letssopt.presentation.library.state.LibraryUiState
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LibraryViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(
        LibraryUiState(
            items = listOf(
                LibraryUiModel(1, R.drawable.img_content1),
                LibraryUiModel(2, R.drawable.img_content2),
                LibraryUiModel(3, R.drawable.img_content3),
                LibraryUiModel(4, R.drawable.img_content1),
                LibraryUiModel(5, R.drawable.img_content2),
                LibraryUiModel(6, R.drawable.img_content3),
                LibraryUiModel(7, R.drawable.img_content1),
            ).toImmutableList()
        )
    )
    val uiState = _uiState.asStateFlow()

    fun deleteItem(id: Int) {
        _uiState.update { current ->
            current.copy(
                items = current.items.filter { it.id != id }.toImmutableList()
            )
        }
    }
}