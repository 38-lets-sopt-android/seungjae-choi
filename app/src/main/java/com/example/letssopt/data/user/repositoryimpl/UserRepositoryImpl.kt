package com.example.letssopt.data.user.repositoryimpl

import com.example.letssopt.core.common.util.suspendRunCatching
import com.example.letssopt.data.user.model.UserInfoModel
import com.example.letssopt.data.user.model.UserItemModel
import com.example.letssopt.data.user.model.toModel
import com.example.letssopt.data.user.remote.datasource.UserRemoteDataSource
import com.example.letssopt.data.user.repository.UserRepository

class UserRepositoryImpl(
    private val dataSource: UserRemoteDataSource
) : UserRepository {

    override suspend fun getUserInfo(userId: Long): Result<UserInfoModel> = suspendRunCatching {
        dataSource.getUserInfo(userId).data!!.toModel()
    }

    override suspend fun getUserList(): Result<List<UserItemModel>> = suspendRunCatching {
        dataSource.getUserList().data!!.users.map { it.toModel() }
    }
}