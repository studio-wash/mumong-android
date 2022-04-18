package com.studiowash.mumong.presentation.activity

import android.annotation.SuppressLint
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.studiowash.mumong.presentation.R
import com.studiowash.mumong.presentation.player.MusicPlayerView2

abstract class MumongActivity: AppCompatActivity() {

    val statusBarHeight: Int get() {
        var result = 0
        val resourceId = applicationContext.resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) result = applicationContext.resources.getDimensionPixelSize(resourceId)
        return result
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.setDecorFitsSystemWindows(false)
        } else {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        initStatusBar()

        val playerView = MusicPlayerView2(this)
        val playerTouchPreventView = View(this).apply {
            background = ColorDrawable(ContextCompat.getColor(this@MumongActivity, R.color.black))
            visibility = View.GONE
            isClickable = true
        }

        addContentView(
            playerTouchPreventView,
            ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        )
        addContentView(
            playerView,
            ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        )

        playerView.post {
            playerView.y = -playerView.playerBodyHeight.toFloat() + statusBarHeight
        }
        playerView.handle.setOnTouchListener { v, event ->
            val action = event.action
            val curY = event.rawY

            val heightExceptStatus = playerView.playerBodyHeight.toFloat() - statusBarHeight
            val minY = -heightExceptStatus
            val maxY = 0f

            when (action) {
                MotionEvent.ACTION_DOWN -> {}
                MotionEvent.ACTION_MOVE -> {
                    playerView.y = (-playerView.playerBodyHeight.toFloat() + curY).coerceIn(minY, maxY)
                    val alpha = (1f + playerView.y / heightExceptStatus) * 0.7f
                    playerTouchPreventView.alpha = alpha
                    playerTouchPreventView.visibility = if (alpha > 0.1f) View.VISIBLE else View.GONE
                }
                MotionEvent.ACTION_UP -> {
                    if (playerView.y < minY + heightExceptStatus/2) {
                        playerView.animate().y(minY).setUpdateListener {
                            val alpha = (1f + playerView.y / heightExceptStatus) * 0.7f
                            playerTouchPreventView.alpha = alpha
                            playerTouchPreventView.visibility = if (alpha > 0.1f) View.VISIBLE else View.GONE
                        }.start()
                    } else {
                        playerView.animate().y(maxY).setUpdateListener {
                            val alpha = (1f + playerView.y / heightExceptStatus) * 0.7f
                            playerTouchPreventView.alpha = alpha
                            playerTouchPreventView.visibility = if (alpha > 0.1f) View.VISIBLE else View.GONE
                        }.start()
                    }
                }
            }
            true
        }
    }

    open fun initStatusBar() = Unit
}