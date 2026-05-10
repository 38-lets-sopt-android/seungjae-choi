package com.example.letssopt.presentation.profile.state

import androidx.compose.runtime.Immutable
import com.example.letssopt.presentation.profile.model.MyInfoUiModel
import com.example.letssopt.presentation.profile.model.UserItemUiModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Immutable
data class ProfileUiState(
    val myInfoState: MyInfoUiModel = MyInfoUiModel(),
    val userListState: ImmutableList<UserItemUiModel> = persistentListOf()
)

sealed interface ProfileSideEffect {
    data class ShowToast(val message: String) : ProfileSideEffect
    data object NavigateToUserList : ProfileSideEffect
}