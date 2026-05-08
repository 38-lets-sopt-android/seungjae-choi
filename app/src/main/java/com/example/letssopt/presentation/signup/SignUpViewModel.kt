package com.example.letssopt.presentation.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.letssopt.core.common.util.UiState
import com.example.letssopt.core.network.RetrofitClient
import com.example.letssopt.data.auth.model.SignUpModel
import com.example.letssopt.data.auth.remote.datasourceimpl.AuthRemoteDataSourceImpl
import com.example.letssopt.data.auth.repository.AuthRepository
import com.example.letssopt.data.auth.repositoryimpl.AuthRepositoryImpl
import com.example.letssopt.presentation.signup.model.SignUpUiModel
import com.example.letssopt.presentation.signup.state.SignUpSideEffect
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SignUpViewModel : ViewModel() {
    private val authRepository: AuthRepository = AuthRepositoryImpl(
        dataSource = AuthRemoteDataSourceImpl(
            authService = RetrofitClient.authService
        )
    )

    private val _uiState = MutableStateFlow<UiState<Unit>>(UiState.Empty)
    val uiState: StateFlow<UiState<Unit>> = _uiState.asStateFlow()

    private val _sideEffect = MutableSharedFlow<SignUpSideEffect>()
    val sideEffect: SharedFlow<SignUpSideEffect> = _sideEffect.asSharedFlow()

    fun signUp(uiModel: SignUpUiModel) = viewModelScope.launch {

        _uiState.value = UiState.Loading

        val request = SignUpModel(
            loginId = uiModel.loginId,
            password = uiModel.password,
            name = uiModel.name,
            email = uiModel.email,
            age = uiModel.age,
            part = uiModel.part
        )

        authRepository.signUp(request)
            .onSuccess {
                _uiState.value = UiState.Success(Unit)
                _sideEffect.emit(SignUpSideEffect.ShowToast("회원가입에 성공했습니다."))
                _sideEffect.emit(SignUpSideEffect.NavigateToSignIn)
            }
            .onFailure { exception ->
                val message = exception.message ?: "회원가입에 실패했습니다."
                _uiState.value = UiState.Loading
                _sideEffect.emit(SignUpSideEffect.ShowToast(message))
            }
    }
}