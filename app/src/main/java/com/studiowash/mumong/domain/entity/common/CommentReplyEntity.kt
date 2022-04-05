package com.studiowash.mumong.domain.entity.common

import com.studiowash.mumong.domain.entity.user.UserEntity
import java.io.Serializable

data class CommentReplyEntity(
    val content: String,
    val writtenTime: String,
    val likeCount: Int,
    val user: UserEntity
) : Serializable