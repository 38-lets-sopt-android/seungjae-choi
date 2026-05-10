package com.example.letssopt.data.auth.repositoryimpl

import com.example.letssopt.core.common.util.suspendRunCatching
import com.example.letssopt.data.auth.model.SignInModel
import com.example.letssopt.data.auth.model.SignInResponseModel
import com.example.letssopt.data.auth.model.SignUpModel
import com.example.letssopt.data.auth.model.toDto
import com.example.letssopt.data.auth.model.toModel
import com.example.letssopt.data.auth.remote.datasource.AuthRemoteDataSource
import com.example.letssopt.data.auth.repository.AuthRepository

class AuthRepositoryImpl(
    private val dataSource: AuthRemoteDataSource
) : AuthRepository {

    override suspend fun signIn(request: SignInModel): Result<SignInResponseModel> = suspendRunCatching {
        dataSource.signIn(request.toDto()).data!!.toModel()
    }

    override suspend fun signUp(request: SignUpModel): Result<Unit> = suspendRunCatching{
        dataSource.signUp(request.toDto())
        Unit
    }
}