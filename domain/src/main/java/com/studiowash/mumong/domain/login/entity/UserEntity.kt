package com.studiowash.mumong.domain.login.entity

import java.io.Serializable

data class UserEntity (
    val email: String = "",
    val nickname: String = "",
    val profileImg: String = "",
    val description: String = ""
) : Serializable