package com.studiowash.mumong.presentation.community.article

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.studiowash.mumong.R
import com.studiowash.mumong.constant.StringKeySet
import com.studiowash.mumong.databinding.ActivityCommunityArticleBinding
import com.studiowash.mumong.domain.community.CommunityArticleItem

class CommunityArticleActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCommunityArticleBinding
    private val viewModel: CommunityArticleActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_community_article)

        val article = intent?.getSerializableExtra(StringKeySet.ARTICLE) as? CommunityArticleItem
        viewModel.setArticle(article)
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.hold, R.anim.slide_out_to_right)
    }
}