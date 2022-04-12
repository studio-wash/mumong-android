package com.studiowash.mumong.domain.community.entity

import com.studiowash.mumong.domain.common.entity.AttachedImageEntity
import com.studiowash.mumong.domain.common.entity.RecordingEntity
import com.studiowash.mumong.domain.common.entity.CommentEntity
import com.studiowash.mumong.domain.login.entity.UserEntity
import java.io.Serializable

// todo: 이후 서버 데이터와 연동
data class CommunityArticleEntity(
    // todo: article 아이템으로 통일
    val title: String,
    val content: String,
    val writtenTime: String,
    val likeCount: Int,
    val commentCount: Int,
    val comments: List<CommentEntity>,
    val user: UserEntity,
    val attachedImages: List<AttachedImageEntity>? = null,
    val recordings: List<RecordingEntity>? = null
) : Serializable