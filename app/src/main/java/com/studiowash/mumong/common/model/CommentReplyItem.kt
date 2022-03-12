package com.studiowash.mumong.common.model

import java.io.Serializable

data class CommentReplyItem(
    val content: String,
    val writtenTime: String,
    val likeCount: Int,
    // todo: 아래 두 개는 이후 user로 통일
    val userName: String,
    val userIconSrc: String
) : Serializable