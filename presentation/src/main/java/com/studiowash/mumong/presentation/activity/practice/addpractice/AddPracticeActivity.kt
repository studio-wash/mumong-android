package com.studiowash.mumong.presentation.activity.practice.addpractice

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.studiowash.mumong.presentation.R
import com.studiowash.mumong.presentation.databinding.ActivityAddPracticeBinding

class AddPracticeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddPracticeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddPracticeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.hold, R.anim.slide_out_to_right)
    }
}