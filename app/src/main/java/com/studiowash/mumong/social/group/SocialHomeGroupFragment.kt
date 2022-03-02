package com.studiowash.mumong.social.group

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.studiowash.mumong.databinding.FragmentSocialContentGroupBinding

class SocialHomeGroupFragment : Fragment() {
    private lateinit var binding: FragmentSocialContentGroupBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSocialContentGroupBinding.inflate(inflater, container, false)
        return binding.root
    }
}