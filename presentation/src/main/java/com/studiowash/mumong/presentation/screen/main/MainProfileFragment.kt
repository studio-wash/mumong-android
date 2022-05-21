package com.studiowash.mumong.presentation.screen.main

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.studiowash.mumong.presentation.screen.MumongFragment
import com.studiowash.mumong.presentation.constant.StringKeySet
import com.studiowash.mumong.presentation.constant.StringValueSet
import com.studiowash.mumong.presentation.databinding.FragmentMainProfileBinding

class MainProfileFragment : MumongFragment(true) {
    private lateinit var binding: FragmentMainProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainProfileBinding.inflate(inflater, container, false)
        initView()
        initOnClick()
        return binding.root
    }

    private fun initView() {
        binding.user = com.studiowash.mumong.domain.login.LoginStatus.currentUser
        binding.root.viewTreeObserver.addOnGlobalLayoutListener {
            initScrollY()
        }
        binding.ivProfile.clipToOutline = true
    }

    private fun initOnClick() {
    }

    private fun initScrollY() {
        val titleView = when (activity?.intent?.getStringExtra(StringKeySet.CATEGORY)) {
            StringValueSet.SOCIAL -> binding.titleSocialCategoryTextView
            StringValueSet.COMMUNITY -> binding.titleCommunityCategoryTextView
            else -> return
        }

        val offsetViewBounds = Rect()
        titleView.getDrawingRect(offsetViewBounds)
        binding.itemsScrollView.offsetDescendantRectToMyCoords(titleView, offsetViewBounds)
        val relativeTop: Int = offsetViewBounds.top

        binding.itemsScrollView.scrollTo(0, relativeTop - 150)
    }
}