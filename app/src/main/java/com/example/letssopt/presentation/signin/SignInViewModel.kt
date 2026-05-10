package com.example.letssopt.presentation.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.letssopt.core.common.extension.parseError
import com.example.letssopt.core.common.util.UiState
import com.example.letssopt.core.data.AuthPreference
import com.example.letssopt.core.network.RetrofitClient
import com.example.letssopt.data.auth.model.SignInModel
import com.example.letssopt.data.auth.remote.datasourceimpl.AuthRemoteDataSourceImpl
import com.example.letssopt.data.auth.repository.AuthRepository
import com.example.letssopt.data.auth.repositoryimpl.AuthRepositoryImpl
import com.example.letssopt.presentation.signin.model.SignInUiModel
import com.example.letssopt.presentation.signin.state.SignInSideEffect
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SignInViewModel : ViewModel() {
    private val authRepository: AuthRepository = AuthRepositoryImpl(
        dataSource = AuthRemoteDataSourceImpl(
            authService = RetrofitClient.authService
        )
    )

    private val _uiState = MutableStateFlow<UiState<Unit>>(UiState.Empty)
    val uiState: StateFlow<UiState<Unit>> = _uiState.asStateFlow()

    private val _sideEffect = MutableSharedFlow<SignInSideEffect>()
    val sideEffect: SharedFlow<SignInSideEffect> = _sideEffect.asSharedFlow()

    fun onSignUpTextClick() {
        viewModelScope.launch {
            _sideEffect.emit(SignInSideEffect.NavigateToSignUp)
        }
    }

    fun signIn(uiModel: SignInUiModel) {
        viewModelScope.launch {
            if (uiModel.loginId.isBlank() || uiModel.password.isBlank()) {
                _sideEffect.emit(SignInSideEffect.ShowToast("아이디와 비밀번호를 입력해주세요."))
                return@launch
            }

            _uiState.value = UiState.Loading

            val request = SignInModel(
                loginId = uiModel.loginId,
                password = uiModel.password
            )

            authRepository.signIn(request)
                .onSuccess { response ->
                    _uiState.value = UiState.Success(Unit)

                    AuthPreference.setLoggedIn(true)
                    AuthPreference.saveAccount(email = request.loginId, password = request.password)
                    AuthPreference.saveUserId(response.userId)

                    _sideEffect.emit(SignInSideEffect.ShowToast("로그인에 성공했습니다."))
                    _sideEffect.emit(SignInSideEffect.NavigateToMain)
                }
                .onFailure { exception ->
                    _uiState.value = UiState.Empty
                    val errorMessage = exception.parseError("로그인에 실패했습니다.")
                    _sideEffect.emit(SignInSideEffect.ShowToast(errorMessage))
                }
        }
    }
}