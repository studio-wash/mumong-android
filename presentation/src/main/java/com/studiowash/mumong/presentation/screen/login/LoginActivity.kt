package com.studiowash.mumong.presentation.screen.login

import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.studiowash.mumong.presentation.R
import com.studiowash.mumong.presentation.databinding.ActivityLoginBinding
import com.studiowash.mumong.presentation.screen.MumongActivity
import com.studiowash.mumong.presentation.screen.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : MumongActivity(true) {
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
        binding.vpTutorial.adapter = TutorialImagePagerAdapter(
            listOf(
                R.drawable.img_login_tutorial_3,
                R.drawable.img_login_tutorial_3,
                R.drawable.img_login_tutorial_3
            )
        )
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
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}