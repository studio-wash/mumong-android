package com.studiowash.mumong.data.model.community

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CommunityPostData (
    @SerialName("post_id") val postId: Int,
    @SerialName("board_id") val boardId: Int,
    @SerialName("user_id") val userId: Int,
    @SerialName("content") val content: String,
    @SerialName("like_count") val likeCount: Int,
    @SerialName("created_at") val createdAt: Long,
    @SerialName("updated_at") val updatedAt: Long
)