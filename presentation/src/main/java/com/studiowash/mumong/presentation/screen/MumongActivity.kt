package com.studiowash.mumong.presentation.screen

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

abstract class MumongActivity(private val needStatusBarPadding: Boolean): AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTranslucentStatusBar()
    }

    private fun setTranslucentStatusBar() {
        if (needStatusBarPadding) {
            window?.apply {
                statusBarColor = Color.TRANSPARENT
                decorView.systemUiVisibility =
                    View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            }
        }
    }

}