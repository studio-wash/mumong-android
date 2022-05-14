package com.studiowash.mumong.presentation.screen.practice.addpractice

import android.os.Bundle
import com.studiowash.mumong.presentation.screen.MumongActivity
import com.studiowash.mumong.presentation.databinding.ActivityAddPracticeBinding

class AddPracticeActivity : MumongActivity(true) {
    private lateinit var binding: ActivityAddPracticeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddPracticeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}