package com.studiowash.mumong.presentation.social.article

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.studiowash.mumong.R
import com.studiowash.mumong.constant.StringKeySet
import com.studiowash.mumong.databinding.ActivitySocialArticleBinding
import com.studiowash.mumong.domain.model.social.SocialArticleItem

class SocialArticleActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySocialArticleBinding
    private val viewModel: SocialArticleActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_social_article)

        val article = intent?.getSerializableExtra(StringKeySet.ARTICLE) as? SocialArticleItem
        viewModel.setArticle(article)
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.hold, R.anim.slide_out_to_right)
    }
}