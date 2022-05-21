package com.studiowash.mumong.domain.common.entity

import com.studiowash.mumong.domain.login.entity.UserEntity
import java.io.Serializable

data class CommentEntity(
    val content: String,
    val writtenTime: String,
    val likeCount: Int,
    val replies: List<CommentReplyEntity>,
    val user: UserEntity
) : Serializable