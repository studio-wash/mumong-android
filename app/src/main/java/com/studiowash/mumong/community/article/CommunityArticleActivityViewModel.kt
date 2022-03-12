package com.studiowash.mumong.community.article

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CommunityArticleActivityViewModel : ViewModel() {
    val articleLiveData get() = _articleLiveData
    private val _articleLiveData = MutableLiveData<CommunityArticleItem?>()

    fun setArticle(article: CommunityArticleItem?) {
        _articleLiveData.value = article
    }
}