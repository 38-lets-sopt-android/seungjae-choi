package com.example.letssopt.data.user.remote.datasource

import com.example.letssopt.core.network.model.BaseResponse
import com.example.letssopt.data.user.remote.dto.response.UserInfoResponseDto
import com.example.letssopt.data.user.remote.dto.response.UserListResponseDto

interface UserRemoteDataSource {
    suspend fun getUserInfo(userId: Long): BaseResponse<UserInfoResponseDto>
    suspend fun getUserList(): BaseResponse<UserListResponseDto>
}