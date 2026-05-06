package com.example.letssopt.presentation.purchase.model

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Immutable

@Immutable
data class PurchaseUiModel(
    val id: Long,
    @param:DrawableRes val imageRes: Int,
    val title: String
)