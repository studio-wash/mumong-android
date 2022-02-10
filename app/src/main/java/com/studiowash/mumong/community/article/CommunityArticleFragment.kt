package com.studiowash.mumong.community.article

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.studiowash.mumong.R
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()
    }

    private fun initToolbar() {
        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        binding.toolbar.inflateMenu(R.menu.toolbar_action_menu)
        binding.toolbar.setOnMenuItemClickListener {
            when(it.itemId) {
                R.id.action_more -> true
                else -> super.onOptionsItemSelected(it)
            }
        }
    }

    private fun initView(article: CommunityArticleItem?) {
        binding.item = article
        // todo : viewmodel로 추후 이동
        binding.likeCountImageView.setOnClickListener {
            it.isSelected = it.isSelected.not()
        }
        binding.bookmarkCountImageView.setOnClickListener {
            it.isSelected = it.isSelected.not()
        }
    }

    private fun initObserve() {

    }

}