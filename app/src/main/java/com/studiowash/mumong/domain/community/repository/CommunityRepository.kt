package com.studiowash.mumong.domain.community.repository

import com.studiowash.mumong.data.community.dto.CommunityArticleDTO
import com.studiowash.mumong.data.response.RequestResult
import retrofit2.http.Field
import retrofit2.http.GET

interface CommunityRepository {
    @GET("community/article")
    suspend fun getArticles(
        @Field("keyword") keyword: String,
        @Field("article_id") articleId: Int, // 마지막으로 검색된 글 번호,
        @Field("board_id") boardId: Int
    ) : RequestResult<List<CommunityArticleDTO>>
}