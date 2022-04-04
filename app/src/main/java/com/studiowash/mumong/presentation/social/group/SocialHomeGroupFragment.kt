package com.studiowash.mumong.presentation.social.group

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.studiowash.mumong.databinding.FragmentSocialHomeGroupBinding

class SocialHomeGroupFragment : Fragment() {
    private lateinit var binding: FragmentSocialHomeGroupBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSocialHomeGroupBinding.inflate(inflater, container, false)
        return binding.root
    }
}