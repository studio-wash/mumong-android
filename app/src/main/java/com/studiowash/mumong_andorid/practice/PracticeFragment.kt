package com.studiowash.mumong_andorid.practice

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.studiowash.mumong_andorid.databinding.FragmentPracticeBinding

class PracticeFragment : Fragment() {
    private lateinit var binding: FragmentPracticeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPracticeBinding.inflate(inflater, container, false)

        initView()

        return binding.root
    }

    private fun initView() {

    }
}