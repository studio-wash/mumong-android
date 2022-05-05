package com.studiowash.mumong.presentation.activity.practice

import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.studiowash.mumong.presentation.R
import com.studiowash.mumong.presentation.activity.MumongActivity
import com.studiowash.mumong.presentation.activity.community.article.CommunityArticleActivityViewModel
import com.studiowash.mumong.presentation.constant.StringKeySet
import com.studiowash.mumong.presentation.databinding.ActivityPracticeDiaryBinding

class PracticeDiaryActivity: MumongActivity(true) {
    private lateinit var binding: ActivityPracticeDiaryBinding
    private val viewModel: CommunityArticleActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_practice_diary)

        val article = intent?.getSerializableExtra(StringKeySet.ARTICLE) as? com.studiowash.mumong.domain.community.entity.CommunityArticleEntity
        viewModel.setArticle(article)
    }
}