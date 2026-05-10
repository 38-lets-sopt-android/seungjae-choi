package com.example.letssopt.data.auth.model

import com.example.letssopt.data.auth.remote.dto.request.SignInRequestDto
import com.example.letssopt.data.auth.remote.dto.response.SignInResponseDto

data class SignInModel(
    val loginId: String,
    val password: String
)

fun SignInModel.toDto() = SignInRequestDto(
    loginId = loginId,
    password = password
)

data class SignInResponseModel(
    val userId: Long
)

fun SignInResponseDto.toModel() = SignInResponseModel(
    userId = userId
)