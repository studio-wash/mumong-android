package com.studiowash.mumong.domain.entity.common

import com.studiowash.mumong.domain.entity.user.UserEntity
import java.io.Serializable

data class CommentEntity(
    val content: String,
    val writtenTime: String,
    val likeCount: Int,
    val replies: List<CommentReplyEntity>,
    val user: UserEntity
) : Serializable