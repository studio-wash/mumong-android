package com.studiowash.mumong.presentation.social.article

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.studiowash.mumong.domain.entity.social.SocialArticleEntity

class SocialArticleActivityViewModel : ViewModel() {
    val articleLiveData get() = _articleLiveData
    private val _articleLiveData = MutableLiveData<SocialArticleEntity?>()

    fun setArticle(article: SocialArticleEntity?) {
        _articleLiveData.value = article
    }
}