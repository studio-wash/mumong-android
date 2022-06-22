package com.studiowash.mumong.presentation.screen.login.join

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.studiowash.mumong.presentation.databinding.FragmentJoinMumongProfileBinding
import com.studiowash.mumong.presentation.screen.MumongFragment

class JoinMumongProfileFragment: MumongFragment(false) {
    private val binding get() = _binding!!
    private var _binding: FragmentJoinMumongProfileBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentJoinMumongProfileBinding.inflate(inflater, container, false)

        initView()
        initOnClick()
        initObserve()

        return binding.root
    }

    private fun initView() {
        binding.etName.requestFocus()
    }

    private fun initOnClick() {
    }

    private fun initObserve() {
    }
}