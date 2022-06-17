package com.studiowash.mumong.presentation.screen.splash

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.studiowash.mumong.presentation.screen.MumongActivity
import com.studiowash.mumong.presentation.screen.login.LoginActivity
import com.studiowash.mumong.presentation.screen.main.MainActivity

class InitActivity : MumongActivity(true) {
    private val viewModel: InitActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initObserve()
        viewModel.getLastLoginInfo()
    }

    private fun initObserve() {
        viewModel.redirectPage.observe(this) {
            val intent = Intent (this,
                if (it == RedirectPage.Login) LoginActivity::class.java
                else MainActivity::class.java
            )
            startActivity(intent)
            finish()
        }
    }
}