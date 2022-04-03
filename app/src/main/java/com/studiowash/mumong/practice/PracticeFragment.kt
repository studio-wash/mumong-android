package com.studiowash.mumong.practice

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.studiowash.mumong.databinding.FragmentPracticeBinding

class PracticeFragment : Fragment(){
    private lateinit var binding: FragmentPracticeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        if (this::binding.isInitialized.not()) binding = FragmentPracticeBinding.inflate(inflater, container, false)
        return binding.root
    }
}