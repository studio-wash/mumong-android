package com.studiowash.mumong.social

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import com.studiowash.mumong.R
import com.studiowash.mumong.databinding.FragmentSocialHomeBinding
import com.studiowash.mumong.profile.ProfileActivity

class SocialHomeFragment : Fragment() {
    private lateinit var binding: FragmentSocialHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSocialHomeBinding.inflate(inflater, container, false)
        initView()
        initOnClick()
        return binding.root
    }

    private fun initView() {
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
        binding.profileIconImageView.setOnClickListener {
            val intent = Intent(context, ProfileActivity::class.java)
            startActivity(intent)
            activity?.overridePendingTransition(R.anim.slide_in_from_right, R.anim.hold)
        }
    }
}