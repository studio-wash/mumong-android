package com.studiowash.mumong.social.article

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SocialArticleActivityViewModel : ViewModel() {
    val articleLiveData get() = _articleLiveData
    private val _articleLiveData = MutableLiveData<SocialArticleItem?>()

    fun setArticle(article: SocialArticleItem?) {
        _articleLiveData.value = article
    }
}