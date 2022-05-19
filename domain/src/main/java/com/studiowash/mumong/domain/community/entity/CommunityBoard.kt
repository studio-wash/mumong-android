package com.studiowash.mumong.domain.community.entity

import java.io.Serializable

data class CommunityBoard (
    val boardName: String, // todo: 이후 서버 데이터와 연동
    val hasNewArticle: Boolean = false,
    val isFavorite: Boolean = false,
    val recentArticleContent: String? = null
): Serializable