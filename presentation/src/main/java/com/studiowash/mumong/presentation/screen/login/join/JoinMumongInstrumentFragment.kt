package com.studiowash.mumong.presentation.screen.login.join

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.studiowash.mumong.presentation.R
import com.studiowash.mumong.presentation.databinding.FragmentJoinMumongInstrumentBinding
import com.studiowash.mumong.presentation.screen.MumongFragment
import com.studiowash.mumong.presentation.screen.main.MainActivity

class JoinMumongInstrumentFragment: MumongFragment(true) {
    private val binding get() = _binding!!
    private var _binding: FragmentJoinMumongInstrumentBinding? = null

    private val activityViewModel: JoinViewModel by activityViewModels()

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
        activityViewModel.moveNextPageEvent.observe(viewLifecycleOwner) {
            activity?.run {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}