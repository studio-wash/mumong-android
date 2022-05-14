package com.studiowash.mumong.data.social.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SocialFeedDTO(
    @SerialName("social_feed_id") val feedId: Int,
    @SerialName("user_id") val userId: Int,
    @SerialName("content") val content: String,
    @SerialName("like_count") val likeCount: Int,
    @SerialName("created_at") val createdAt: Long,
    @SerialName("updated_at") val updatedAt: Long,
    @SerialName("private") val private: Int // 0: 전체 공개, 1: 친구 공개, 2: 나만 보기
)