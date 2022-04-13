package com.studiowash.mumong.domain.common

sealed class RequestResult<T> {
    data class Fail<T>(var code: Int = 0, var message: String? = null) : RequestResult<T>()
    data class Success<T>(val data: T) : RequestResult<T>()
}