package com.studiowash.mumong

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.studiowash.mumong.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initNavigation()
    }

    private fun initNavigation() {
        supportFragmentManager.findFragmentById(R.id.nav_host_fragment)?.findNavController()?.let { navController ->
            binding.bottomNavigationView.setupWithNavController(navController)

        }
        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.homeFragmentNav -> {
                    binding.navHostFragment.findNavController().navigate(R.id.homeFragmentNav)
                    true
                }
                R.id.practiceNestedNav -> {
                    binding.navHostFragment.findNavController().navigate(R.id.practiceNestedNav)
                    true
                }
                R.id.socialFragmentNav -> {
                    binding.navHostFragment.findNavController().navigate(R.id.socialFragmentNav)
                    true
                }
                R.id.communityFragmentNav -> {
                    binding.navHostFragment.findNavController().navigate(R.id.communityFragmentNav)
                    true
                }
                else -> false
            }
        }
    }
}