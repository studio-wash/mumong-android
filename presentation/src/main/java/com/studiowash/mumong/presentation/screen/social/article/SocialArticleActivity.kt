package com.studiowash.mumong.presentation.screen.social.article

import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.studiowash.mumong.domain.social.entity.SocialArticleEntity
import com.studiowash.mumong.presentation.R
import com.studiowash.mumong.presentation.screen.MumongActivity
import com.studiowash.mumong.presentation.constant.StringKeySet
import com.studiowash.mumong.presentation.databinding.ActivitySocialArticleBinding

class SocialArticleActivity : MumongActivity(true) {
    private lateinit var binding: ActivitySocialArticleBinding
    private val viewModel: SocialArticleActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_social_article)

        val article = intent?.getSerializableExtra(StringKeySet.ARTICLE) as? SocialArticleEntity
        viewModel.setArticle(article)
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.hold, R.anim.slide_out_to_right)
    }
}