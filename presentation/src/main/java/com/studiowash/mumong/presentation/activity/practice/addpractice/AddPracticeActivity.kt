package com.studiowash.mumong.presentation.activity.practice.addpractice

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.studiowash.mumong.presentation.R
import com.studiowash.mumong.presentation.activity.MumongActivity
import com.studiowash.mumong.presentation.databinding.ActivityAddPracticeBinding

class AddPracticeActivity : MumongActivity(true) {
    private lateinit var binding: ActivityAddPracticeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddPracticeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}