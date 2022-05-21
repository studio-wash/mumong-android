package com.studiowash.mumong.domain.common.entity

import com.studiowash.mumong.domain.login.entity.UserEntity
import java.io.Serializable

data class CommentReplyEntity(
    val content: String,
    val writtenTime: String,
    val likeCount: Int,
    val user: UserEntity
) : Serializable