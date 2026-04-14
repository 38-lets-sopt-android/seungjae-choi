package com.example.letssopt.core.common.util

import android.util.Patterns

enum class AuthValidationError(val message: String) {
    INVALID_EMAIL("올바른 이메일 형식이 아닙니다."),
    INVALID_PASSWORD_LENGTH("비밀번호는 8~12글자여야 합니다."),
    PASSWORD_MISMATCH("비밀번호가 일치하지 않습니다."),
    EMPTY_EMAIL("이메일을 입력해주세요."),
    EMPTY_PASSWORD("비밀번호를 입력해주세요.")
}

object SoptValidator {
    fun validateSignUpInputs(
        email: String,
        password: String,
        passwordCheck: String
    ): AuthValidationError? {
        return when {
            email.isBlank() -> AuthValidationError.EMPTY_EMAIL
            !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> AuthValidationError.INVALID_EMAIL
            password.isBlank() -> AuthValidationError.EMPTY_PASSWORD
            password.length !in 8..12 -> AuthValidationError.INVALID_PASSWORD_LENGTH
            password != passwordCheck -> AuthValidationError.PASSWORD_MISMATCH
            else -> null
        }
    }

    fun validateSignInInputs(
        email: String,
        password: String
    ): AuthValidationError? {
        return when {
            email.isBlank() -> AuthValidationError.EMPTY_EMAIL
            password.isBlank() -> AuthValidationError.EMPTY_PASSWORD
            else -> null
        }
    }
}