package com.studiowash.mumong.presentation.screen.login

import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.studiowash.mumong.domain.login.LoginStatus
import com.studiowash.mumong.presentation.R
import com.studiowash.mumong.presentation.databinding.ActivityLoginBinding
import com.studiowash.mumong.presentation.screen.MumongActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : MumongActivity(true) {
    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        initOnClick()
    }

    private fun initOnClick() {
        binding.btnKakaoLogin.setOnClickListener {
            viewModel.requestKakaoLogin(this)
        }
    }
}