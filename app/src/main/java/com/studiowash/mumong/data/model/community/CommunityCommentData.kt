package com.studiowash.mumong.data.model.community

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CommunityCommentData (
    @SerialName("comment_id") val commentId: Int, // 댓글 번호
    @SerialName("article_id") val articleId: Int,
    @SerialName("user_id") val userId: Int,
    @SerialName("content") val content: String,
    @SerialName("like_count") val likeCount: Int = 0,
    @SerialName("comment_no") val commentNo: Int, // 동일 댓글 내 순서
    @SerialName("parent") val parent: Int, // 부모댓글(대댓글 아닐 시 본인 번호 사용)
    @SerialName("created_at") val createdAt: Long
)