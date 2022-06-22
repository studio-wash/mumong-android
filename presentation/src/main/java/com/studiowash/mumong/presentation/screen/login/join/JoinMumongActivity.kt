package com.studiowash.mumong.presentation.screen.login.join

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.studiowash.mumong.presentation.R
import com.studiowash.mumong.presentation.databinding.ActivityJoinMumongBinding
import com.studiowash.mumong.presentation.screen.MumongActivity

class JoinMumongActivity: MumongActivity(false) {
    private lateinit var binding: ActivityJoinMumongBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_join_mumong)

        initView()
        initOnClick()
        initObserve()
    }

    private fun initView() {
    }

    private fun initViewPager() {
    }

    private fun initOnClick() {
    }

    private fun initObserve() {
    }
}