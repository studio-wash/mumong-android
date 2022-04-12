package com.studiowash.mumong.domain.social.entity

import com.studiowash.mumong.domain.login.entity.UserEntity

data class OnlineFriendEntity (
    val user: UserEntity,
    val isOnline: Boolean
)