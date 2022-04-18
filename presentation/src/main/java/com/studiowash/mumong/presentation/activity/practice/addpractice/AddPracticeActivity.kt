package com.studiowash.mumong.presentation.activity.practice.addpractice

import android.os.Bundle
import com.studiowash.mumong.presentation.R
import com.studiowash.mumong.presentation.activity.MumongActivity
import com.studiowash.mumong.presentation.databinding.ActivityAddPracticeBinding

class AddPracticeActivity : MumongActivity() {
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