package com.studiowash.mumong

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.studiowash.mumong.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initBottomNavigation()
    }

    private fun initBottomNavigation() {
        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.page_home -> {
                    binding.navHostFragment.findNavController().navigate(R.id.homeFragment)
                    true
                }
                R.id.page_practice -> {
                    binding.navHostFragment.findNavController().navigate(R.id.practiceFragment)
                    true
                }
                R.id.page_social -> {
                    binding.navHostFragment.findNavController().navigate(R.id.socialFragment)
                    true
                }
                R.id.page_community -> {
                    binding.navHostFragment.findNavController().navigate(R.id.communityFragment)
                    true
                }
                else -> false
            }
        }
    }

    override fun onBackPressed() {
        if (binding.bottomNavigationView.selectedItemId == R.id.page_home) {
            finish()
        } else {
            binding.bottomNavigationView.selectedItemId = R.id.page_home
        }
    }
}