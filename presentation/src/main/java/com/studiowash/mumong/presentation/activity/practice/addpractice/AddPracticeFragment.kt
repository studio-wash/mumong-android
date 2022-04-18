package com.studiowash.mumong.presentation.activity.practice.addpractice

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.studiowash.mumong.presentation.R
import com.studiowash.mumong.presentation.databinding.FragmentPracticeAddPracticeBinding

class AddPracticeFragment : Fragment(R.layout.fragment_practice_add_practice) {
    private val binding get() = _binding!!
    private var _binding : FragmentPracticeAddPracticeBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentPracticeAddPracticeBinding.bind(view)
    }
}