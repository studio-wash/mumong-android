package com.studiowash.mumong.domain.community.repository

import com.studiowash.mumong.domain.common.RequestResult
import com.studiowash.mumong.domain.community.entity.CommunityArticleEntity
import kotlinx.coroutines.flow.Flow

interface CommunityRepository {
    fun getArticles(
        keyword: String,
        articleId: Int, // 마지막으로 검색된 글 번호,
        boardId: Int
    ) : Flow<RequestResult<List<CommunityArticleEntity>>>
}