package com.studiowash.mumong.domain.model.social

import com.studiowash.mumong.domain.model.user.UserItem

data class OnlineFriendItem (
    val user: UserItem,
    val isOnline: Boolean
)