package com.studiowash.mumong.profile

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.studiowash.mumong.constant.StringKeySet
import com.studiowash.mumong.databinding.FragmentProfileBinding
import com.studiowash.mumong.singleton.LoginObject

class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        initView()
        initOnClick()
        return binding.root
    }

    private fun initView() {
        binding.user = LoginObject.currentUser
        binding.root.viewTreeObserver.addOnGlobalLayoutListener {
            initScrollY()
        }
    }

    private fun initOnClick() {
        binding.closeButtonImageView.setOnClickListener {
            activity?.finish()
        }
    }

    private fun initScrollY() {
        val titleView = when (activity?.intent?.getStringExtra(StringKeySet.CATEGORY)) {
            StringKeySet.SOCIAL -> binding.titleSocialCategoryTextView
            StringKeySet.COMMUNITY -> binding.titleCommunityCategoryTextView
            else -> return
        }

        val offsetViewBounds = Rect()
        titleView.getDrawingRect(offsetViewBounds)
        binding.itemsScrollView.offsetDescendantRectToMyCoords(titleView, offsetViewBounds)
        val relativeTop: Int = offsetViewBounds.top

        binding.itemsScrollView.scrollTo(0, relativeTop - 150)
    }
}