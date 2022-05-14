package com.studiowash.mumong.presentation.screen.community.search

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.studiowash.mumong.presentation.R
import com.studiowash.mumong.presentation.databinding.ActivityCommunityBinding
import com.studiowash.mumong.presentation.databinding.ActivityCommunitySearchBinding
import com.studiowash.mumong.presentation.screen.MumongActivity

class CommunitySearchActivity: MumongActivity(true) {
    private lateinit var binding: ActivityCommunitySearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_community_search)
    }
}