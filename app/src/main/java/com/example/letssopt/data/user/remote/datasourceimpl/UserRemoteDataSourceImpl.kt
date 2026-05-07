package com.example.letssopt.data.user.remote.datasourceimpl

import com.example.letssopt.core.network.model.BaseResponse
import com.example.letssopt.data.user.remote.api.UserService
import com.example.letssopt.data.user.remote.datasource.UserRemoteDataSource
import com.example.letssopt.data.user.remote.dto.response.UserInfoResponseDto
import com.example.letssopt.data.user.remote.dto.response.UserListResponseDto

class UserRemoteDataSourceImpl(
    private val userService: UserService
) : UserRemoteDataSource {
    override suspend fun getUserInfo(userId: Long): BaseResponse<UserInfoResponseDto> =
        userService.getUserInfo(userId)

    override suspend fun getUserList(): BaseResponse<UserListResponseDto> =
        userService.getUserList()
}