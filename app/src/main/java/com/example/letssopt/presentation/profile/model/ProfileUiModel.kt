package com.example.letssopt.presentation.profile.model

import androidx.compose.runtime.Immutable

@Immutable
data class MyInfoUiModel(
    val loginId: String = "",
    val name: String = "",
    val email: String = "",
    val age: String = "",
    val part: String = ""
)

@Immutable
data class UserItemUiModel(
    val id: Long,
    val name: String,
    val part: String
)