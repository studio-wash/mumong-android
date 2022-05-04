package com.studiowash.mumong.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.studiowash.mumong.presentation.R
import com.studiowash.mumong.presentation.databinding.ActivityMainBinding
import com.studiowash.mumong.presentation.player.MusicChangeListener
import com.studiowash.mumong.presentation.player.MusicPlayer
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var isDraggingMusicTrackBar = false

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
        binding.musicPlayerView.onClickPlayPause = { wasPlaying ->
            if (wasPlaying) {
                MusicPlayer.pause()
            } else {
                MusicPlayer.start()
            }
        }
        binding.musicPlayerView.onClickClose = {
            MusicPlayer.stop()
            binding.showMusicPlayer = false
        }
    }

    private fun initNavigation() {
        binding.bottomNavigationView.itemIconTintList = null
        supportFragmentManager.findFragmentById(R.id.nav_host_fragment)?.findNavController()?.let { navController ->
            binding.bottomNavigationView.setupWithNavController(navController)
        }
    }

    private fun onUpdateCurrentMusic(recording: com.studiowash.mumong.domain.common.entity.RecordingEntity?) {
        binding.musicPlayerView.apply {
            currentRecording = recording
            isPlaying = true
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

    private val musicChangeListener = object : MusicChangeListener {
        override fun onMusicChanged(recording: com.studiowash.mumong.domain.common.entity.RecordingEntity?) {
            onUpdateCurrentMusic(recording)
        }

        override fun onMusicPrepared(durationMilli: Int) {
            binding.musicPlayerView.totalSeconds = durationMilli / MusicPlayer.SECOND_IN_MILLI
        }

        override fun onMusicPlayStateChanged(state: MusicPlayer.MusicPlayState) {
            when(state) {
                MusicPlayer.MusicPlayState.Play -> {
                    binding.showMusicPlayer = true
                    binding.musicPlayerView.isPlaying = true
                }
                MusicPlayer.MusicPlayState.Pause -> {
                    binding.musicPlayerView.isPlaying = false
                }
                MusicPlayer.MusicPlayState.Stop -> {
                    binding.showMusicPlayer = false
                    binding.musicPlayerView.isPlaying = false
                }
            }
        }

        override fun onUpdatePosition(currentMilli: Int) {
            if (isDraggingMusicTrackBar) return
            binding.musicPlayerView.currentSeconds = currentMilli / MusicPlayer.SECOND_IN_MILLI
        }
    }

}