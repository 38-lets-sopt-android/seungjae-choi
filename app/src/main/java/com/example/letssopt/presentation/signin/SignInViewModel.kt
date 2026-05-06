package com.example.letssopt.presentation.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.letssopt.core.common.util.SoptValidator
import com.example.letssopt.core.data.AuthPreference
import com.example.letssopt.presentation.signin.state.SignInSideEffect
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class SignInViewModel : ViewModel() {

    private val _sideEffect = MutableSharedFlow<SignInSideEffect>()
    val sideEffect: SharedFlow<SignInSideEffect> = _sideEffect.asSharedFlow()

    fun onSignUpTextClick() {
        viewModelScope.launch {
            _sideEffect.emit(SignInSideEffect.NavigateToSignUp)
        }
    }

    fun signIn(email: String, password: String) {
        viewModelScope.launch {
            val storedEmail = AuthPreference.getEmail()
            val storedPassword = AuthPreference.getPassword()

            val errorType = SoptValidator.validateSignInInputs(email, password)
            if (errorType != null) {
                _sideEffect.emit(SignInSideEffect.ShowToast(errorType.message))
                return@launch
            }

            if (email != storedEmail || password != storedPassword) {
                _sideEffect.emit(SignInSideEffect.ShowToast("아이디 또는 비밀번호가 일치하지 않습니다."))
            } else {
                AuthPreference.setLoggedIn(true)
                _sideEffect.emit(SignInSideEffect.ShowToast("로그인에 성공했습니다"))
                _sideEffect.emit(SignInSideEffect.NavigateToMain)
            }
        }
    }
}