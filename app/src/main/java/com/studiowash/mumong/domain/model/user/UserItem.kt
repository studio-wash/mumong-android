package com.studiowash.mumong.domain.model.user

import java.io.Serializable

data class UserItem (
    val email: String = "",
    val nickname: String = "",
    val profileImg: String = "",
    val description: String = ""
) : Serializable