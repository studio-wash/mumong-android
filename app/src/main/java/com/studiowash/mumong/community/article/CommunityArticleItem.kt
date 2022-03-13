package com.studiowash.mumong.community.article

import com.studiowash.mumong.common.model.AttachedImageItem
import com.studiowash.mumong.common.model.RecordingItem
import com.studiowash.mumong.common.model.CommentItem
import com.studiowash.mumong.common.model.User
import java.io.Serializable

// todo: 이후 서버 데이터와 연동
data class CommunityArticleItem(
    // todo: article 아이템으로 통일
    val title: String,
    val content: String,
    val writtenTime: String,
    val likeCount: Int,
    val commentCount: Int,
    val comments: List<CommentItem>,
    val user: User,
    val attachedImages: List<AttachedImageItem>? = null,
    val recordings: List<RecordingItem>? = null
) : Serializable