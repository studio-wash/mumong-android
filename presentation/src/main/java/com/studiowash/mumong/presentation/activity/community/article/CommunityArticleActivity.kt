package com.studiowash.mumong.presentation.activity.community.article

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.studiowash.mumong.presentation.R
import com.studiowash.mumong.presentation.activity.MumongActivity
import com.studiowash.mumong.presentation.constant.StringKeySet
import com.studiowash.mumong.presentation.databinding.ActivityCommunityArticleBinding

class CommunityArticleActivity : MumongActivity(true) {
    private lateinit var binding: ActivityCommunityArticleBinding
    private val viewModel: CommunityArticleActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_community_article)

        val article = intent?.getSerializableExtra(StringKeySet.ARTICLE) as? com.studiowash.mumong.domain.community.entity.CommunityArticleEntity
        viewModel.setArticle(article)
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.hold, R.anim.slide_out_to_right)
    }
}