package com.example.letssopt.presentation.purchase.state

import androidx.compose.runtime.Immutable
import com.example.letssopt.R
import com.example.letssopt.presentation.purchase.model.PurchaseUiModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList

@Immutable
data class PurchaseUiState(
    val items: ImmutableList<PurchaseUiModel> = persistentListOf()
) {
    companion object {
        val dummyItems = listOf(
            PurchaseUiModel(1L, R.drawable.img_content1, "이 사람 통역 되나요"),
            PurchaseUiModel(2L, R.drawable.img_content2, "이상한일5"),
            PurchaseUiModel(3L, R.drawable.img_content3, "하일매리"),
            PurchaseUiModel(4L, R.drawable.img_content1, "이 사람 통역 되나요"),
            PurchaseUiModel(5L, R.drawable.img_content2, "이상한일5")
        ).toImmutableList()
    }
}

sealed interface PurchaseSideEffect {
    data class ShowToast(val message: String) : PurchaseSideEffect
}