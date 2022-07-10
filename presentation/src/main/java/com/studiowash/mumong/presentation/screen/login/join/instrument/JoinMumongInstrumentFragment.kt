package com.studiowash.mumong.presentation.screen.login.join.instrument

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.studiowash.mumong.presentation.databinding.FragmentJoinMumongInstrumentBinding
import com.studiowash.mumong.presentation.screen.MumongFragment
import com.studiowash.mumong.presentation.screen.main.MainActivity

class JoinMumongInstrumentFragment: MumongFragment(true) {
    private val binding get() = _binding!!
    private var _binding: FragmentJoinMumongInstrumentBinding? = null

    private val viewModel: JoinMumongInstrumentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentJoinMumongInstrumentBinding.inflate(inflater, container, false)

        initView()
        initOnClick()
        initObserve()

        viewModel.checkDataAvailability() // for test

        return binding.root
    }

    private fun initView() {
    }

    private fun initOnClick() {
        binding.btnNext.setOnClickListener {
            viewModel.moveToMainActivity()
        }
    }

    private fun initObserve() {
        viewModel.availableData.observe(viewLifecycleOwner) {
            binding.availableData = it
        }
        viewModel.moveMainActivityEvent.observe(viewLifecycleOwner) {
            activity?.run {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}