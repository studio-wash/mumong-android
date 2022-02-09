package com.studiowash.mumong.community

// todo: 이후 서버 데이터와 연동
data class MumongPickArticleItem (
    // todo: 카테고리 분류
    val pickedCategory: String,
    // todo: article 아이템으로 통일
    val title: String,
    // todo: 아래 두 개는 이후 user로 통일
    val userName: String,
    val userIconSrc: String
)