package com.example.letssopt.data.user.model

import com.example.letssopt.data.user.remote.dto.response.UserDto
import com.example.letssopt.data.user.remote.dto.response.UserInfoResponseDto

data class UserInfoModel(
    val id: Long,
    val loginId: String,
    val name: String,
    val email: String,
    val age: Int,
    val part: String
)

fun UserInfoResponseDto.toModel() = UserInfoModel(
    id = id,
    loginId = loginId,
    name = name,
    email = email,
    age = age,
    part = part
)

data class UserItemModel(
    val id: Long,
    val name: String,
    val part: String
)

fun UserDto.toModel() = UserItemModel(
    id = id,
    name = name,
    part = part
)