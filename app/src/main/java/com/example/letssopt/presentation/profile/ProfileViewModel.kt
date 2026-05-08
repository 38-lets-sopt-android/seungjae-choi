package com.example.letssopt.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.letssopt.core.common.util.UiState
import com.example.letssopt.core.data.AuthPreference
import com.example.letssopt.core.network.RetrofitClient
import com.example.letssopt.data.user.remote.datasourceimpl.UserRemoteDataSourceImpl
import com.example.letssopt.data.user.repository.UserRepository
import com.example.letssopt.data.user.repositoryimpl.UserRepositoryImpl
import com.example.letssopt.presentation.profile.model.MyInfoUiModel
import com.example.letssopt.presentation.profile.model.UserItemUiModel
import com.example.letssopt.presentation.profile.state.ProfileSideEffect
import com.example.letssopt.presentation.profile.state.ProfileUiState
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {
    private val userRepository: UserRepository = UserRepositoryImpl(
        UserRemoteDataSourceImpl(RetrofitClient.userService)
    )

    private val _uiState = MutableStateFlow<UiState<ProfileUiState>>(UiState.Loading)
    val uiState = _uiState.asStateFlow()

    private val _sideEffect = MutableSharedFlow<ProfileSideEffect>()
    val sideEffect: SharedFlow<ProfileSideEffect> = _sideEffect.asSharedFlow()

    fun fetchMyInfo() {
        viewModelScope.launch {
            val userId = AuthPreference.getUserId()
            val currentState = (_uiState.value as? UiState.Success)?.data ?: ProfileUiState()

            if (userId == -1L) {
                _uiState.update { UiState.Loading }
                return@launch
            }

            userRepository.getUserInfo(userId)
                .onSuccess { data ->
                    _uiState.update {
                        UiState.Success(
                            currentState.copy(
                                myInfoState = MyInfoUiModel(
                                    loginId = data.loginId,
                                    name = data.name,
                                    email = data.email,
                                    age = data.age.toString(),
                                    part = data.part
                                )
                            )
                        )
                    }
                }
                .onFailure { exception ->
                    _uiState.update { UiState.Loading }
                    _sideEffect.emit(ProfileSideEffect.ShowToast("프로필 조회 실패: ${exception.message}"))
                }
        }
    }

    fun fetchUserList() {
        viewModelScope.launch {
            val currentState = (_uiState.value as? UiState.Success)?.data ?: ProfileUiState()

            userRepository.getUserList()
                .onSuccess { list ->
                    _uiState.update {
                        UiState.Success(
                            currentState.copy(
                                userListState = list.map {
                                    UserItemUiModel(id = it.id, name = it.name, part = it.part)
                                }.toImmutableList()
                            )
                        )
                    }
                }
                .onFailure { exception ->
                    _uiState.update { UiState.Loading }
                    _sideEffect.emit(ProfileSideEffect.ShowToast("유저 목록 조회 실패: ${exception.message}"))
                }
        }
    }

    fun onNavigateToUserList() {
        viewModelScope.launch {
            _sideEffect.emit(ProfileSideEffect.NavigateToUserList)
        }
    }
}