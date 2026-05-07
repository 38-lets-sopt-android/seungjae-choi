package com.example.letssopt.data.auth.remote.datasource

import com.example.letssopt.core.network.model.BaseResponse
import com.example.letssopt.data.auth.remote.dto.request.SignInRequestDto
import com.example.letssopt.data.auth.remote.dto.request.SignUpRequestDto
import com.example.letssopt.data.auth.remote.dto.response.SignInResponseDto

interface AuthRemoteDataSource {
    suspend fun signIn(request: SignInRequestDto): BaseResponse<SignInResponseDto>
    suspend fun signUp(request: SignUpRequestDto): BaseResponse<Unit>
}