package com.example.letssopt.presentation.library

import androidx.lifecycle.ViewModel
import com.example.letssopt.presentation.library.state.LibraryUiState
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LibraryViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(
        LibraryUiState(
            items = LibraryUiState.dummyItems.toImmutableList()
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