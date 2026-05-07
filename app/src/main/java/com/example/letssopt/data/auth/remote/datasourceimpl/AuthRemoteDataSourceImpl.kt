package com.example.letssopt.data.auth.remote.datasourceimpl

import com.example.letssopt.core.network.model.BaseResponse
import com.example.letssopt.data.auth.remote.api.AuthService
import com.example.letssopt.data.auth.remote.datasource.AuthRemoteDataSource
import com.example.letssopt.data.auth.remote.dto.request.SignInRequestDto
import com.example.letssopt.data.auth.remote.dto.request.SignUpRequestDto
import com.example.letssopt.data.auth.remote.dto.response.SignInResponseDto

class AuthRemoteDataSourceImpl (
    private val authService: AuthService
) : AuthRemoteDataSource {
    override suspend fun signIn(request: SignInRequestDto): BaseResponse<SignInResponseDto> =
        authService.signIn(request)

    override suspend fun signUp(request: SignUpRequestDto): BaseResponse<Unit> =
        authService.signUp(request)
}