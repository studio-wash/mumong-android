package com.studiowash.mumong.domain.entity.social

import com.studiowash.mumong.domain.entity.user.UserEntity

data class OnlineFriendEntity (
    val user: UserEntity,
    val isOnline: Boolean
)