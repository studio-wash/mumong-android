package com.studiowash.mumong.presentation.screen.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2
import com.studiowash.mumong.presentation.R
import com.studiowash.mumong.presentation.databinding.ActivityLoginBinding
import com.studiowash.mumong.presentation.screen.MumongActivity
import com.studiowash.mumong.presentation.screen.login.join.JoinMumongActivity
import com.studiowash.mumong.presentation.screen.main.MainActivity
import com.studiowash.mumong.presentation.util.setTextCrossFade
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity: MumongActivity(false) {
    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        initView()
        initOnClick()
        initObserve()
    }

    private fun initView() {
        initViewPager()
    }

    private fun initViewPager() {
        binding.vpTutorial.adapter = TutorialImagePagerAdapter(listOf(
            TutorialContents.Page1,
            TutorialContents.Page2,
            TutorialContents.Page3
        ))
        binding.uiTutorialIndicatorDots.attachToPager(binding.vpTutorial)
        binding.vpTutorial.currentItem = 0
    }

    private fun initOnClick() {
        binding.btnKakaoLogin.setOnClickListener {
            viewModel.requestKakaoLogin(this)
        }
        binding.btnNaverLogin.setOnClickListener {
            viewModel.requestNaverLogin(this)
        }
    }

    private fun initObserve() {
        viewModel.currentUser.observe(this) {
            if (it != null) {
                val intent = Intent(this, JoinMumongActivity::class.java)
                startActivity(intent)
            }
//            if (it != null) {
//                val intent = Intent(this, MainActivity::class.java)
//                startActivity(intent)
//                finish()
//            }
        }
    }
}