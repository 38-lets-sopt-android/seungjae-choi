package com.example.letssopt.presentation.library

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.letssopt.core.data.LibraryDao
import com.example.letssopt.presentation.library.model.LibraryUiModel
import com.example.letssopt.presentation.library.state.LibrarySideEffect
import com.example.letssopt.presentation.library.state.LibraryUiState
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class LibraryViewModel(
    private val libraryDao: LibraryDao
) : ViewModel() {
    private val _sideEffect = MutableSharedFlow<LibrarySideEffect>()
    val sideEffect: SharedFlow<LibrarySideEffect> = _sideEffect.asSharedFlow()

    val uiState = libraryDao.getAllItems()
        .map { entities ->
            LibraryUiState(
                items = entities.map { entity ->
                    LibraryUiModel(
                        id = entity.id,
                        imageRes = entity.imageRes,
                        title = entity.title
                    )
                }.toImmutableList()
            )
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), LibraryUiState())



    fun deleteItem(id: Long) {
        viewModelScope.launch {
            val entity = libraryDao.getItemById(id) ?: return@launch
            libraryDao.delete(entity)
            _sideEffect.emit(LibrarySideEffect.ShowToast("보관함에서 삭제되었습니다."))
        }
    }
}