package com.example.letssopt.presentation.signup

import androidx.lifecycle.ViewModel
import com.example.letssopt.core.common.util.SoptValidator

class SignUpViewModel : ViewModel() {
    fun validateSignUp(
        email: String,
        password: String,
        passwordCheck: String
    ): String? {
        val errorType = SoptValidator.validateSignUpInputs(email, password, passwordCheck)
        return errorType?.message
    }
}