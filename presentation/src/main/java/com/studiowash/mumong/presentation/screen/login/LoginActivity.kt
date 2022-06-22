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
        binding.vpTutorial.adapter = TutorialImagePagerAdapter(tutorialImageResources)
        binding.uiTutorialIndicatorDots.attachToPager(binding.vpTutorial)
        binding.vpTutorial.currentItem = 0
            binding.vpTutorial.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                binding.tvTutorialTitle.setTextCrossFade(tutorialTitleResources[position])
                binding.tvTutorialContent.setTextCrossFade(tutorialContentResources[position])
            }
        })
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

    companion object {
        private val tutorialImageResources = listOf(
            R.drawable.img_tutorial_1,
            R.drawable.img_tutorial_2,
            R.drawable.img_tutorial_3
        )
        private val tutorialTitleResources = listOf(
            R.string.login_tutorial_1_title,
            R.string.login_tutorial_2_title,
            R.string.login_tutorial_3_title
        )
        private  val tutorialContentResources = listOf(
            R.string.login_tutorial_1_content,
            R.string.login_tutorial_2_content,
            R.string.login_tutorial_3_content
        )
    }
}