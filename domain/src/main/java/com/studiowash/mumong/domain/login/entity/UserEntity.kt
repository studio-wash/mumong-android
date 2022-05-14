package com.studiowash.mumong.domain.login.entity

import java.io.Serializable

data class UserEntity(
    val email: String = "",
    val socialNickname: String = "",
    val communityNickname: String = "",
    val profileImg: String = "",
    val description: String = ""
) : Serializable