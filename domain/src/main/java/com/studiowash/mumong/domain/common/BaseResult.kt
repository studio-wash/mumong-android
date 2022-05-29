package com.studiowash.mumong.domain.common

sealed class BaseResult<out T: Any, out U: Any> {
    data class Success<T: Any>(val data: T) : BaseResult<T, Nothing>()
    data class Fail<U: Any>(val data: U) : BaseResult<Nothing, U>()
}

class NetworkError(val code: Int, val message: String)