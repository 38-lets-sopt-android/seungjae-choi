package com.example.letssopt.presentation.signin

import androidx.lifecycle.ViewModel
import com.example.letssopt.core.common.util.SoptValidator

class SignInViewModel : ViewModel() {
    fun validateSignIn(
        email: String,
        password: String,
        storedEmail: String,
        storedPassword: String
    ): String? {
        val errorType = SoptValidator.validateSignInInputs(email, password)

        if (errorType != null) {
            return errorType.message
        }

        if (email != storedEmail || password != storedPassword) {
            return "아이디 또는 비밀번호가 일치하지 않습니다."
        }

        return null
    }
}