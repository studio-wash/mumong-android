package com.studiowash.mumong.domain.common

sealed class RequestResult<T> {
    data class Fail<T>(val code: Int = 0, var message: String?) : RequestResult<T>()
    data class Success<T>(val data: T) : RequestResult<T>()
}