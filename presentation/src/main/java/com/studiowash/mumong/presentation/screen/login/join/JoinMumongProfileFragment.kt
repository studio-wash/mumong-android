package com.studiowash.mumong.presentation.screen.login.join

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.studiowash.mumong.presentation.R
import com.studiowash.mumong.presentation.databinding.FragmentJoinMumongProfileBinding
import com.studiowash.mumong.presentation.screen.MumongFragment

class JoinMumongProfileFragment: MumongFragment(false) {
    private val binding get() = _binding!!
    private var _binding: FragmentJoinMumongProfileBinding? = null

    private val viewModel: JoinMumongProfileViewModel by viewModels()

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
        binding.etId.doAfterTextChanged {
            viewModel.checkDataAvailability(it.toString(), binding.etName.text.toString())
        }
        binding.etName.doAfterTextChanged {
            viewModel.checkDataAvailability(binding.etId.text.toString(), it.toString())
        }
    }

    private fun initOnClick() {
        binding.btnNext.setOnClickListener {
            viewModel.moveToNextPage()
        }
    }

    private fun initObserve() {
        viewModel.moveNextPageEvent.observe(viewLifecycleOwner) {
            findNavController().navigate(R.id.action_joinProfileFragmentNav_to_joinInstrumentFragmentNav)
        }
        viewModel.availableData.observe(viewLifecycleOwner) {
            binding.availableData = it
        }
    }
}