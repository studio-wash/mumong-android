package com.studiowash.mumong.domain.social

import com.studiowash.mumong.domain.common.User

data class OnlineFriendItem (
    val user: User,
    val isOnline: Boolean
)