package com.studiowash.mumong.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.studiowash.mumong.R
import com.studiowash.mumong.domain.model.common.RecordingItem
import com.studiowash.mumong.databinding.ActivityMainBinding
import com.studiowash.mumong.module.sound.MusicChangeListener
import com.studiowash.mumong.module.sound.MusicPlayer

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val musicChangeListener = object :MusicChangeListener {
        override fun onMusicChanged(recording: RecordingItem?) {
            onUpdateCurrentMusic(recording)
        }

        override fun onMusicPrepared(durationMilli: Int) {
            binding.musicPlayerView.totalSeconds = durationMilli / MusicPlayer.SECOND_IN_MILLI
        }

        override fun onUpdatePosition(currentMilli: Int) {
            binding.musicPlayerView.currentSeconds = currentMilli / MusicPlayer.SECOND_IN_MILLI
            println("currentSeconds ${currentMilli / MusicPlayer.SECOND_IN_MILLI}")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initOnClick()
        initNavigation()
    }

    private fun initOnClick() {
        binding.musicPlayerView.setOnClickListener {
            MusicPlayer.currentMusic = null // todo : this is just for testing
        }
    }

    private fun initNavigation() {
        binding.bottomNavigationView.itemIconTintList = null
        supportFragmentManager.findFragmentById(R.id.nav_host_fragment)?.findNavController()?.let { navController ->
            binding.bottomNavigationView.setupWithNavController(navController)
        }
    }

    private fun onUpdateCurrentMusic(recording: RecordingItem?) {
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