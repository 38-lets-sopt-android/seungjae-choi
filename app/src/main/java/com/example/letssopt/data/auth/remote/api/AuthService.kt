package com.example.letssopt.data.auth.remote.api

import com.example.letssopt.core.network.model.BaseResponse
import com.example.letssopt.data.auth.remote.dto.request.SignInRequestDto
import com.example.letssopt.data.auth.remote.dto.request.SignUpRequestDto
import com.example.letssopt.data.auth.remote.dto.response.SignInResponseDto
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("api/v1/auth/signup")
    suspend fun signUp(@Body request: SignUpRequestDto): BaseResponse<Unit>

    @POST("api/v1/auth/signin")
    suspend fun signIn(@Body request: SignInRequestDto): BaseResponse<SignInResponseDto>
}