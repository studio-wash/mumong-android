package com.studiowash.mumong.presentation.screen.login.join

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.studiowash.mumong.presentation.databinding.FragmentJoinMumongInstrumentBinding
import com.studiowash.mumong.presentation.screen.MumongFragment

class JoinMumongInstrumentFragment: MumongFragment(true) {
    private val binding get() = _binding!!
    private var _binding: FragmentJoinMumongInstrumentBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentJoinMumongInstrumentBinding.inflate(inflater, container, false)

        initView()
        initOnClick()
        initObserve()

        return binding.root
    }

    private fun initView() {
    }

    private fun initOnClick() {
    }

    private fun initObserve() {
    }
}