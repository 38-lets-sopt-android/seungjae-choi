package com.example.letssopt.data.auth.model

import com.example.letssopt.data.auth.remote.dto.request.SignUpRequestDto

data class SignUpModel (
    val loginId: String,
    val password: String,
    val name: String,
    val email: String,
    val age: Int,
    val part: String
)

fun SignUpModel.toDto() = SignUpRequestDto(
    loginId = loginId,
    password = password,
    name = name,
    email = email,
    age = age,
    part = part
)