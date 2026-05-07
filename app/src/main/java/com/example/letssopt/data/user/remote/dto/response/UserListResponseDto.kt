package com.example.letssopt.data.user.remote.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserListResponseDto(
    @SerialName("users")
    val users: List<UserDto>
)

@Serializable
data class UserDto(
    @SerialName("id")
    val id: Long,
    @SerialName("name")
    val name: String,
    @SerialName("part")
    val part: String
)