package com.example.letssopt.data.auth.repository

import com.example.letssopt.data.auth.model.SignInModel
import com.example.letssopt.data.auth.model.SignInResponseModel
import com.example.letssopt.data.auth.model.SignUpModel

interface AuthRepository {
    suspend fun signIn(request: SignInModel): Result<SignInResponseModel>
    suspend fun signUp(request: SignUpModel): Result<Unit>
}