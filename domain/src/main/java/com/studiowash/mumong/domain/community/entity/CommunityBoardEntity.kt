package com.studiowash.mumong.domain.community.entity

data class CommunityBoardEntity (
    val boardName: String, // todo: 이후 서버 데이터와 연동
    val hasNewArticle: Boolean = false,
    val isFavorite: Boolean = false,
    val recentArticleContent: String? = null
)