package com.studiowash.mumong.presentation.screen.login

import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.studiowash.mumong.presentation.R
import com.studiowash.mumong.presentation.databinding.ActivityLoginBinding
import com.studiowash.mumong.presentation.screen.MumongActivity
import com.studiowash.mumong.presentation.util.ToastUtil
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : MumongActivity(true) {
    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        initOnClick()
        initObserve()
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
                ToastUtil.showToast(this, "Login Success")
                setResult(RESULT_OK)
                finish()
            }
        }
    }
}