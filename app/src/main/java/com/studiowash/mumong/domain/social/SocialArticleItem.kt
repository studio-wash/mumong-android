package com.studiowash.mumong.domain.social

import com.studiowash.mumong.domain.common.AttachedImageItem
import com.studiowash.mumong.domain.common.RecordingItem
import com.studiowash.mumong.domain.common.User
import java.io.Serializable

// todo: 이후 서버 데이터와 연동
data class SocialArticleItem(
    // todo: article 아이템으로 통일
    val content: String,
    val writtenTime: String,
    val likeCount: Int,
    val commentCount: Int,
    val user: User,
    val attachedImages: List<AttachedImageItem>? = null,
    val recordings: List<RecordingItem>? = null
) : Serializable