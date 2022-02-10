package com.studiowash.mumong.social.friend

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.studiowash.mumong.databinding.FragmentSocialContentFriendBinding

class SocialContentFriendFragment : Fragment() {
    private lateinit var binding: FragmentSocialContentFriendBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSocialContentFriendBinding.inflate(inflater, container, false)
        return binding.root
    }
}