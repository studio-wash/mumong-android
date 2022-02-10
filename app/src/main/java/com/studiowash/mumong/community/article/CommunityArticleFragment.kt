package com.studiowash.mumong.community.article

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.studiowash.mumong.databinding.FragmentCommunityArticleBinding

class CommunityArticleFragment : Fragment() {
    private lateinit var binding: FragmentCommunityArticleBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCommunityArticleBinding.inflate(inflater, container, false)

        val article = arguments?.getSerializable("ARTICLE") as? CommunityArticleItem

        initView(article)
        initObserve()
        return binding.root
    }

    private fun initView(article: CommunityArticleItem?) {
        binding.item = article
    }

    private fun initObserve() {

    }

}