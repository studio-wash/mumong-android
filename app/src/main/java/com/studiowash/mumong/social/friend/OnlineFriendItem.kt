package com.studiowash.mumong.social.friend

import com.studiowash.mumong.common.model.User

data class OnlineFriendItem (
    val user: User,
    val isOnline: Boolean
)