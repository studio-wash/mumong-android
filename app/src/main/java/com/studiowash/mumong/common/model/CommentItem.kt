package com.studiowash.mumong.common.model

import java.io.Serializable

data class CommentItem(
    val content: String,
    val writtenTime: String,
    val likeCount: Int,
    val replies: List<CommentReplyItem>,
    val user: User
) : Serializable