package com.studiowash.mumong.domain.community.repository

import com.studiowash.mumong.domain.common.BaseResult
import com.studiowash.mumong.domain.common.NetworkError
import com.studiowash.mumong.domain.community.entity.CommunityArticle
import kotlinx.coroutines.flow.Flow

interface CommunityRepository {
    fun getArticles(
        keyword: String,
        articleId: Int, // 마지막으로 검색된 글 번호,
        boardId: Int
    ) : Flow<BaseResult<List<CommunityArticle>, NetworkError>>
}