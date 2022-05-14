package com.studiowash.mumong.presentation.screen.community

import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.studiowash.mumong.domain.community.entity.CommunityBoardEntity
import com.studiowash.mumong.domain.social.entity.SocialArticleEntity
import com.studiowash.mumong.presentation.R
import com.studiowash.mumong.presentation.screen.MumongActivity
import com.studiowash.mumong.presentation.screen.community.article.CommunityArticleViewModel
import com.studiowash.mumong.presentation.constant.StringKeySet
import com.studiowash.mumong.presentation.databinding.ActivityCommunityBinding

class CommunityActivity : MumongActivity(true) {
    private lateinit var binding: ActivityCommunityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_community)

        val board = intent?.getSerializableExtra(StringKeySet.BOARD) as CommunityBoardEntity
        val args = CommunityBoardFragmentArgs(board).toBundle()

        findNavControllerOnCreate(R.id.content_view).setGraph(R.navigation.nav_graph_community_activity, args)
    }
}