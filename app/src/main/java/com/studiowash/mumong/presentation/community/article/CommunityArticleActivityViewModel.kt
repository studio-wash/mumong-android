package com.studiowash.mumong.presentation.community.article

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.studiowash.mumong.domain.model.community.CommunityArticleItem

class CommunityArticleActivityViewModel : ViewModel() {
    val articleLiveData get() = _articleLiveData
    private val _articleLiveData = MutableLiveData<CommunityArticleItem?>()

    fun setArticle(article: CommunityArticleItem?) {
        _articleLiveData.value = article
    }
}