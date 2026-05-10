package com.example.letssopt.data.user.remote.api

import com.example.letssopt.core.network.model.BaseResponse
import com.example.letssopt.data.user.remote.dto.response.UserInfoResponseDto
import com.example.letssopt.data.user.remote.dto.response.UserListResponseDto
import retrofit2.http.GET
import retrofit2.http.Path

interface UserService {
    @GET("api/v1/users/{userId}")
    suspend fun getUserInfo(
        @Path("userId") userId: Long
    ): BaseResponse<UserInfoResponseDto>

    @GET("api/v1/users")
    suspend fun getUserList(): BaseResponse<UserListResponseDto>
}