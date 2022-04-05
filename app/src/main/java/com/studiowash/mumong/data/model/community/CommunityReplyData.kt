package com.studiowash.mumong.data.model.community

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CommunityReplyData (
    @SerialName("reply_id") val replyId: Int, // 댓글 번호
    @SerialName("post_id") val postId: Int,
    @SerialName("user_id") val userId: Int,
    @SerialName("content") val content: String,
    @SerialName("like_count") val likeCount: Int = 0,
    @SerialName("reply_no") val replyNo: Int, // 동일 댓글 내 순서
    @SerialName("parent") val parent: Int, // 부모댓글(대댓글 아닐 시 본인 번호 사용)
    @SerialName("created_at") val createdAt: Long
)