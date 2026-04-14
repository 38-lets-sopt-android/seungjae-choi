package com.example.letssopt.presentation.signin

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.letssopt.core.common.util.SoptValidator

class SignInViewModel : ViewModel() {
    var registeredEmail by mutableStateOf("")
        private set

    var registeredPassword by mutableStateOf("")
        private set

    fun updateRegisteredInfo(email: String, password: String) {
        registeredEmail = email
        registeredPassword = password
    }

    fun validateSignIn(
        email: String,
        password: String
    ): String? {
        val errorType = SoptValidator.validateSignInInputs(email, password)

        if (errorType != null) {
            return errorType.message
        }

        if (email != registeredEmail || password != registeredPassword) {
            return "아이디 또는 비밀번호가 일치하지 않습니다."
        }

        return null
    }
}