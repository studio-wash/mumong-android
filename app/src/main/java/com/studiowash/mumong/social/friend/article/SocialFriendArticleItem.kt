package com.studiowash.mumong.social.friend.article

import com.studiowash.mumong.common.model.AttachedImageItem
import com.studiowash.mumong.common.model.AttachedRecordingItem
import java.io.Serializable

// todo: 이후 서버 데이터와 연동
data class SocialFriendArticleItem (
    // todo: article 아이템으로 통일
    val content: String,
    val writtenTime: String,
    val likeCount: Int,
    val commentCount: Int,
    // todo: 아래 두 개는 이후 user로 통일
    val userName: String,
    val userIconSrc: String,
    val attachedImages: List<AttachedImageItem>? = null,
    val attachedRecordings: List<AttachedRecordingItem>? = null
) : Serializable