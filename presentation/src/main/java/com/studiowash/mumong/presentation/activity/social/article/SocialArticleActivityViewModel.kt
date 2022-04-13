package com.studiowash.mumong.presentation.activity.social.article

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SocialArticleActivityViewModel : ViewModel() {
    val articleLiveData get() = _articleLiveData
    private val _articleLiveData = MutableLiveData<com.studiowash.mumong.domain.social.entity.SocialArticleEntity?>()

    fun setArticle(article: com.studiowash.mumong.domain.social.entity.SocialArticleEntity?) {
        _articleLiveData.value = article
    }
}