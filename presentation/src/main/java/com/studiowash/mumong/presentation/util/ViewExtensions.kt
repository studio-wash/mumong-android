package com.studiowash.mumong.presentation.util

import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.TextView
import androidx.annotation.StringRes

fun TextView.setTextCrossFade(@StringRes textRes: Int) {
    val fadeInAnim = AlphaAnimation(0f, 1f).apply { duration = 200 }
    val fadeOutAnim = AlphaAnimation(1f, 0f).apply { duration = 200 }
    fadeOutAnim.setAnimationListener(object: Animation.AnimationListener {
        override fun onAnimationStart(p0: Animation?) {}
        override fun onAnimationRepeat(p0: Animation?) {}
        override fun onAnimationEnd(p0: Animation?) {
            setText(textRes)
            startAnimation(fadeInAnim)
        }
    })

    post {
        if (this.text.isBlank()) {
            setText(textRes)
            startAnimation(fadeInAnim)
        } else startAnimation(fadeOutAnim)
    }
}