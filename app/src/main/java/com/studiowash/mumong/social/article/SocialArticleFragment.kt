package com.studiowash.mumong.social.article

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.studiowash.mumong.R
import com.studiowash.mumong.common.adapter.AttachedImageAdapter
import com.studiowash.mumong.common.adapter.AttachedRecordingAdapter
import com.studiowash.mumong.databinding.FragmentSocialArticleBinding
import com.studiowash.mumong.social.friend.article.SocialFriendArticleItem

class SocialArticleFragment : Fragment() {
    private lateinit var binding: FragmentSocialArticleBinding

    private val attachedRecordingAdapter = AttachedRecordingAdapter({}, {})
    private val attachedImageAdapter = AttachedImageAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSocialArticleBinding.inflate(inflater, container, false)

        val article = arguments?.getSerializable("ARTICLE") as? SocialFriendArticleItem

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

    private fun initView(article: SocialFriendArticleItem?) {
        binding.item = article

        // todo : viewmodel로 추후 이동
        // SERVER API : 좋아요, 북마크 결과 받아서 ui에 적용 가능한 api
        binding.likeButtonLinearLayout.setOnClickListener {
            binding.isLiked = binding.isLiked.not()
        }
        binding.bookmarkButtonLinearLayout.setOnClickListener {
            binding.isBookmarked = binding.isBookmarked.not()
        }

        binding.recordListRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = attachedRecordingAdapter
            itemAnimator = null
        }
        attachedRecordingAdapter.items = article?.attachedRecordings ?: emptyList()

        binding.imageListRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = attachedImageAdapter
            itemAnimator = null
        }
        attachedImageAdapter.items = article?.attachedImages ?: emptyList()
    }

    private fun initObserve() {

    }

}