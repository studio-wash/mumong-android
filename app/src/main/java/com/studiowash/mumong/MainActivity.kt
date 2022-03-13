package com.studiowash.mumong

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.studiowash.mumong.databinding.ActivityMainBinding
import com.studiowash.mumong.singleton.MusicChangeListener
import com.studiowash.mumong.singleton.MusicPlayer

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val musicChangeListener = MusicChangeListener { src ->
        binding.currentMusicSrc = src
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initMusicPlayer()
        initNavigation()
    }

    private fun initMusicPlayer() {
        MusicPlayer.addOnMusicChangeListener(musicChangeListener)
    }

    private fun initNavigation() {
        binding.bottomNavigationView.itemIconTintList = null
        supportFragmentManager.findFragmentById(R.id.nav_host_fragment)?.findNavController()?.let { navController ->
            binding.bottomNavigationView.setupWithNavController(navController)
        }
    }

    override fun onDestroy() {
        MusicPlayer.removeOnMusicChangeListener(musicChangeListener)
        super.onDestroy()
    }
}