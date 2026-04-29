package com.example.letssopt.presentation.purchase

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.letssopt.core.data.LibraryDao
import com.example.letssopt.core.data.LibraryEntity
import com.example.letssopt.presentation.purchase.state.PurchaseSideEffect
import com.example.letssopt.presentation.purchase.state.PurchaseUiState
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PurchaseViewModel(
    private val libraryDao: LibraryDao
) : ViewModel() {
    private val _uiState = MutableStateFlow(
        PurchaseUiState(
            items = PurchaseUiState.dummyItems.toImmutableList()
        )
    )
    val uiState = _uiState.asStateFlow()

    private val _sideEffect = MutableSharedFlow<PurchaseSideEffect>()
    val sideEffect: SharedFlow<PurchaseSideEffect> = _sideEffect.asSharedFlow()

    fun onPurchaseClick(id: Long, title: String, imageRes: Int) {
        viewModelScope.launch {
            val existing = libraryDao.getItemById(id)

            if (existing != null) {
                _sideEffect.emit(PurchaseSideEffect.ShowToast("이미 보관함에 있는 작품입니다."))
                return@launch
            }

            libraryDao.insert(LibraryEntity(id = id, title = title, imageRes = imageRes))
            _sideEffect.emit(PurchaseSideEffect.ShowToast("${title}을(를) 보관함에 담았습니다!"))
        }
    }
}