package com.studiowash.mumong.common.model

import java.io.Serializable

data class CommentReplyItem(
    val content: String,
    val writtenTime: String,
    val likeCount: Int,
    val user: User
) : Serializable