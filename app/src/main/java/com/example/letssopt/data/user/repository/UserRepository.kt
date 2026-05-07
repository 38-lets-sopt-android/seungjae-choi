package com.example.letssopt.data.user.repository

import com.example.letssopt.data.user.model.UserInfoModel
import com.example.letssopt.data.user.model.UserItemModel

interface UserRepository {
    suspend fun getUserInfo(userId: Long): Result<UserInfoModel>
    suspend fun getUserList(): Result<List<UserItemModel>>
}