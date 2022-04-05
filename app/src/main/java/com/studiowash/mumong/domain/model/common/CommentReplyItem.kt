package com.studiowash.mumong.domain.model.common

import com.studiowash.mumong.domain.model.user.UserItem
import java.io.Serializable

data class CommentReplyItem(
    val content: String,
    val writtenTime: String,
    val likeCount: Int,
    val user: UserItem
) : Serializable