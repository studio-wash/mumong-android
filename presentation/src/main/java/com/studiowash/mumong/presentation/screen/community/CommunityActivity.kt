package com.studiowash.mumong.presentation.screen.community

import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.studiowash.mumong.presentation.R
import com.studiowash.mumong.presentation.screen.MumongActivity
import com.studiowash.mumong.presentation.screen.community.article.CommunityArticleViewModel
import com.studiowash.mumong.presentation.constant.StringKeySet
import com.studiowash.mumong.presentation.databinding.ActivityCommunityBinding

class CommunityActivity : MumongActivity(true) {
    private lateinit var binding: ActivityCommunityBinding
    private val viewModel: CommunityArticleViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_community)

        val article = intent?.getSerializableExtra(StringKeySet.ARTICLE) as? com.studiowash.mumong.domain.community.entity.CommunityArticleEntity
        viewModel.setArticle(article)
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.hold, R.anim.slide_out_to_right)
    }
}