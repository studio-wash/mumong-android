package com.studiowash.mumong.domain.entity.user

import java.io.Serializable

data class UserEntity (
    val email: String = "",
    val nickname: String = "",
    val profileImg: String = "",
    val description: String = ""
) : Serializable