package com.studiowash.mumong.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CommunityArticleDTO (
    @SerialName("article_id") val articleId: Int,
    @SerialName("board_id") val boardId: Int,
    @SerialName("user_id") val userId: Int,
    @SerialName("content") val content: String,
    @SerialName("like_count") val likeCount: Int,
    @SerialName("created_at") val createdAt: Long,
    @SerialName("updated_at") val updatedAt: Long
)