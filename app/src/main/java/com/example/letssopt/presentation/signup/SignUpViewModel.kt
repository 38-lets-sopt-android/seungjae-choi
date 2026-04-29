package com.example.letssopt.presentation.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.letssopt.core.common.util.SoptValidator
import com.example.letssopt.core.data.AuthPreference
import com.example.letssopt.presentation.signup.state.SignUpSideEffect
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class SignUpViewModel : ViewModel() {

    private val _sideEffect = MutableSharedFlow<SignUpSideEffect>()
    val sideEffect: SharedFlow<SignUpSideEffect> = _sideEffect.asSharedFlow()

    fun signUp(
        email: String,
        password: String,
        passwordCheck: String
    ) {
        viewModelScope.launch {
            val errorType = SoptValidator.validateSignUpInputs(email, password, passwordCheck)

            if (errorType != null) {
                _sideEffect.emit(SignUpSideEffect.ShowToast(errorType.message))
            } else {
                AuthPreference.saveAccount(email, password)
                _sideEffect.emit(SignUpSideEffect.ShowToast("회원가입에 성공했습니다."))
                _sideEffect.emit(SignUpSideEffect.NavigateToSignIn)
            }
        }
    }
}