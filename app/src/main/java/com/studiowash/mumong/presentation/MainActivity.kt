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
import com.studiowash.mumong.module.sound.MusicPlayService

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val musicChangeListener = MusicChangeListener { recording ->
        onUpdateCurrentMusic(recording)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initOnClick()
        initNavigation()
    }

    private fun initOnClick() {
        binding.musicPlayerView.setOnClickListener {
            MusicPlayService.currentMusic = null // todo : this is just for testing
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
        binding.musicPlayerView.currentRecording = recording
    }

    override fun onResume() {
        super.onResume()
        onUpdateCurrentMusic(MusicPlayService.currentMusic)
        MusicPlayService.addOnMusicChangeListener(musicChangeListener)
    }

    override fun onPause() {
        MusicPlayService.removeOnMusicChangeListener(musicChangeListener)
        super.onPause()
    }
}