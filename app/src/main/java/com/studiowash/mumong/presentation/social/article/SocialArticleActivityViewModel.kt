package com.studiowash.mumong.presentation.social.article

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.studiowash.mumong.domain.social.SocialArticleItem

class SocialArticleActivityViewModel : ViewModel() {
    val articleLiveData get() = _articleLiveData
    private val _articleLiveData = MutableLiveData<SocialArticleItem?>()

    fun setArticle(article: SocialArticleItem?) {
        _articleLiveData.value = article
    }
}