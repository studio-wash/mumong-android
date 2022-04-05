package com.studiowash.mumong.domain.model.community

import com.studiowash.mumong.domain.model.common.AttachedImageItem
import com.studiowash.mumong.domain.model.common.RecordingItem
import com.studiowash.mumong.domain.model.common.CommentItem
import com.studiowash.mumong.domain.model.user.UserItem
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
    val user: UserItem,
    val attachedImages: List<AttachedImageItem>? = null,
    val recordings: List<RecordingItem>? = null
) : Serializable