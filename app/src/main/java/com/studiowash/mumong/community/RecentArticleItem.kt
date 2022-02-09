package com.studiowash.mumong.community

// todo: 이후 서버 데이터와 연동
data class RecentArticleItem (
    // todo: article 아이템으로 통일
    val title: String,
    val content: String,
    val writtenTime: String,
    val likeCount: Int,
    val commentCount: Int,
    // todo: 아래 두 개는 이후 user로 통일
    val userName: String,
    val userIconSrc: String,
)