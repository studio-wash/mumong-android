package com.studiowash.mumong.presentation.community.article

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.studiowash.mumong.domain.community.entity.CommunityArticleEntity

class CommunityArticleActivityViewModel : ViewModel() {
    val articleLiveData get() = _articleLiveData
    private val _articleLiveData = MutableLiveData<CommunityArticleEntity?>()

    fun setArticle(article: CommunityArticleEntity?) {
        _articleLiveData.value = article
    }
}