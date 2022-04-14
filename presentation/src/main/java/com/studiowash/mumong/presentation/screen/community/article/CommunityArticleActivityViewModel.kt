package com.studiowash.mumong.presentation.screen.community.article

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CommunityArticleActivityViewModel : ViewModel() {
    val articleLiveData get() = _articleLiveData
    private val _articleLiveData = MutableLiveData<com.studiowash.mumong.domain.community.entity.CommunityArticleEntity?>()

    fun setArticle(article: com.studiowash.mumong.domain.community.entity.CommunityArticleEntity?) {
        _articleLiveData.value = article
    }
}