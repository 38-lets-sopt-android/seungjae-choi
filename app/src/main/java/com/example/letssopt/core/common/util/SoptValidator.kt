package com.example.letssopt.core.common.util

import android.util.Patterns

object SoptValidator {
    fun validateSignUpInputs(
        email: String,
        password: String,
        passwordCheck: String
    ): String? {
        return when {
            !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> "올바른 이메일 형식이 아닙니다."
            password.length !in 8..12 -> "비밀번호는 8~12글자여야 합니다."
            password != passwordCheck -> "비밀번호가 일치하지 않습니다."
            else -> null
        }
    }

    fun validateSignInInputs(
        email: String,
        password: String
    ): String? {
        return when {
            email.isBlank() -> "이메일을 입력해주세요."
            password.isBlank() -> "비밀번호를 입력해주세요."
            else -> null
        }
    }
}