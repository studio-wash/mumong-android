package com.studiowash.mumong.domain.entity.social

import com.studiowash.mumong.domain.entity.common.AttachedImageEntity
import com.studiowash.mumong.domain.entity.common.RecordingEntity
import com.studiowash.mumong.domain.entity.user.UserEntity
import java.io.Serializable

// todo: 이후 서버 데이터와 연동
data class SocialArticleEntity(
    // todo: article 아이템으로 통일
    val content: String,
    val writtenTime: String,
    val likeCount: Int,
    val commentCount: Int,
    val user: UserEntity,
    val attachedImages: List<AttachedImageEntity>? = null,
    val recordings: List<RecordingEntity>? = null
) : Serializable