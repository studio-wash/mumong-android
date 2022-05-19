package com.studiowash.mumong.presentation.screen.community.article

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.studiowash.mumong.domain.community.entity.CommunityArticle

class CommunityArticleViewModel : ViewModel() {
    val articleLiveData get() = _articleLiveData
    private val _articleLiveData = MutableLiveData<CommunityArticle?>()

    fun setArticle(article: CommunityArticle?) {
        _articleLiveData.value = article
    }
}