package com.studiowash.mumong.presentation.screen.login.join

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.activityViewModels
import com.studiowash.mumong.presentation.databinding.FragmentJoinMumongProfileBinding
import com.studiowash.mumong.presentation.screen.MumongFragment

class JoinMumongProfileFragment: MumongFragment(false) {
    private val binding get() = _binding!!
    private var _binding: FragmentJoinMumongProfileBinding? = null

    private val activityViewModel: JoinViewModel by activityViewModels()

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
        binding.etId.doOnTextChanged { text, start, before, count ->
            activityViewModel.setDataAvailability(checkDataAvailability())
        }
        binding.etName.doOnTextChanged { text, start, before, count ->
            activityViewModel.setDataAvailability(checkDataAvailability())
        }
    }

    private fun checkDataAvailability() : Boolean {
        return binding.etId.text?.length?:0 > 0 && binding.etName.text?.length?:0 > 0
    }

    private fun initOnClick() {
    }

    private fun initObserve() {
    }
}