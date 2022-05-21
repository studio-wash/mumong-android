package com.studiowash.mumong.presentation.screen.community

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.studiowash.mumong.domain.community.entity.CommunityBoard
import com.studiowash.mumong.presentation.R
import com.studiowash.mumong.presentation.screen.MumongActivity
import com.studiowash.mumong.presentation.constant.StringKeySet
import com.studiowash.mumong.presentation.databinding.ActivityCommunityBinding

class CommunityActivity : MumongActivity(true) {
    private lateinit var binding: ActivityCommunityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_community)

        val board = intent?.getSerializableExtra(StringKeySet.BOARD) as CommunityBoard
        val args = CommunityBoardFragmentArgs(board).toBundle()

        findNavControllerOnCreate(R.id.content_view).setGraph(R.navigation.nav_graph_community_activity, args)
    }
}