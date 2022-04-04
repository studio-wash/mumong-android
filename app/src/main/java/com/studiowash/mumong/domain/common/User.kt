package com.studiowash.mumong.domain.common

import java.io.Serializable

data class User (
    val email: String = "",
    val nickname: String = "",
    val profileImg: String = "",
    val description: String = ""
) : Serializable