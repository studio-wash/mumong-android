package com.studiowash.mumong.domain.entity.response

object ResponseCode {
    const val EXCEPTION_UNKNOWN_STATUS = -1
    const val HTTP_STATUS_OK = 200
    const val HTTP_STATUS_UNAUTHORIZED = 401
    const val HTTP_STATUS_FORBIDDEN = 403
    const val HTTP_STATUS_NOT_FOUND = 404
    const val HTTP_STATUS_INTERNAL_SERVER_ERROR = 500
}