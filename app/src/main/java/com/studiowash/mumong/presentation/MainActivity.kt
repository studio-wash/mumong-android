package com.studiowash.mumong.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.studiowash.mumong.R
import com.studiowash.mumong.domain.common.entity.RecordingEntity
import com.studiowash.mumong.databinding.ActivityMainBinding
import com.studiowash.mumong.module.sound.MusicChangeListener
import com.studiowash.mumong.module.sound.MusicPlayer
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var isDraggingMusicTrackBar = false

    private val musicChangeListener = object :MusicChangeListener {
        override fun onMusicChanged(recording: RecordingEntity?) {
            onUpdateCurrentMusic(recording)
        }

        override fun onMusicPrepared(durationMilli: Int) {
            binding.musicPlayerView.totalSeconds = durationMilli / MusicPlayer.SECOND_IN_MILLI
        }

        override fun onUpdatePosition(currentMilli: Int) {
            if (isDraggingMusicTrackBar) return
            binding.musicPlayerView.currentSeconds = currentMilli / MusicPlayer.SECOND_IN_MILLI
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initOnClick()
        initNavigation()
    }

    private fun initOnClick() {
        // todo : seekbar listener 전체를 옮기기
        binding.musicPlayerView.onStartTrackingTouchListener = {
            isDraggingMusicTrackBar = true
        }
        binding.musicPlayerView.onStopTrackingTouchListener = {
            MusicPlayer.seekPercent(it.progress)
            isDraggingMusicTrackBar = false
        }
    }

    private fun initNavigation() {
        binding.bottomNavigationView.itemIconTintList = null
        supportFragmentManager.findFragmentById(R.id.nav_host_fragment)?.findNavController()?.let { navController ->
            binding.bottomNavigationView.setupWithNavController(navController)
        }
    }

    private fun onUpdateCurrentMusic(recording: RecordingEntity?) {
        binding.showMusicPlayer = recording != null
        binding.musicPlayerView.apply {
            currentRecording = recording
        }
    }

    override fun onResume() {
        super.onResume()
        onUpdateCurrentMusic(MusicPlayer.currentMusic)
        MusicPlayer.addOnMusicChangeListener(musicChangeListener)
    }

    override fun onPause() {
        MusicPlayer.removeOnMusicChangeListener(musicChangeListener)
        super.onPause()
    }
}