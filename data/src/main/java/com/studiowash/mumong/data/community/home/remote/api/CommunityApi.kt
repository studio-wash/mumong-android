package com.studiowash.mumong.data.community.home.remote.api

import com.studiowash.mumong.data.CommunityArticleDTO
import com.studiowash.mumong.domain.common.BaseResult
import retrofit2.http.Field
import retrofit2.http.GET

interface CommunityApi {
    @GET("community/article")
    suspend fun getArticles(
        @Field("keyword") keyword: String,
        @Field("article_id") articleId: Int, // 마지막으로 검색된 글 번호,
        @Field("board_id") boardId: Int
    ) : BaseResult<List<CommunityArticleDTO>, Throwable>
}