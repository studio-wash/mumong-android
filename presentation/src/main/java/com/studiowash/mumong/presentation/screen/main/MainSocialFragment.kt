package com.studiowash.mumong.presentation.screen.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import com.studiowash.mumong.presentation.R
import com.studiowash.mumong.presentation.databinding.FragmentMainSocialBinding
import com.studiowash.mumong.presentation.screen.MumongFragment

class MainSocialFragment : MumongFragment(true) {
    private lateinit var binding: FragmentMainSocialBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainSocialBinding.inflate(inflater, container, false)
        initView()
        initOnClick()
        return binding.root
    }

    private fun initView() {
        binding.ivProfile.clipToOutline = true

        val innerNavController = (childFragmentManager.findFragmentById(R.id.social_home_content_view) as NavHostFragment).navController
        binding.friendPageButtonLinearLayout.setOnClickListener{
            binding.pageIndex = 0
            innerNavController.navigate(R.id.socialHomeFriendFragmentNav)
        }
        binding.groupPageButtonLinearLayout.setOnClickListener {
            binding.pageIndex = 1
            innerNavController.navigate(R.id.socialHomeGroupFragmentNav)
        }
    }

    private fun initOnClick() {
    }
}