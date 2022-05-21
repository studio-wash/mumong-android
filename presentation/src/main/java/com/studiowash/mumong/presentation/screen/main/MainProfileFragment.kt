package com.studiowash.mumong.presentation.screen.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.studiowash.mumong.domain.login.LoginStatus
import com.studiowash.mumong.presentation.databinding.FragmentMainProfileBinding
import com.studiowash.mumong.presentation.screen.MumongFragment

class MainProfileFragment : MumongFragment(true) {
    private lateinit var binding: FragmentMainProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainProfileBinding.inflate(inflater, container, false)
        initView()
        initOnClick()
        return binding.root
    }

    private fun initView() {
        binding.user = LoginStatus.currentUser
        binding.ivProfile.clipToOutline = true

        binding.showAlertRedDot = true
    }

    private fun initOnClick() {
    }
}