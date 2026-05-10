package com.example.letssopt.core.common.extension

import com.example.letssopt.core.network.RetrofitClient
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import retrofit2.HttpException

fun Throwable.parseError(defaultMessage: String = "네트워크 오류가 발생했습니다."): String {
    if (this !is HttpException) return this.message ?: defaultMessage

    val errorBody = response()?.errorBody()?.string()
    return if (!errorBody.isNullOrBlank()) {
        try {
            val jsonElement = RetrofitClient.json.parseToJsonElement(errorBody)
            jsonElement.jsonObject["message"]?.jsonPrimitive?.content ?: "오류 발생 (${code()})"
        } catch (e: Exception) {
            "오류 발생 (${code()}): ${message()}"
        }
    } else {
        "서버 응답 없음 (${code()})"
    }
}