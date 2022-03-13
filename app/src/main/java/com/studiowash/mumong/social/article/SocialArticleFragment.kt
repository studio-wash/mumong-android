package com.studiowash.mumong.social.article

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.studiowash.mumong.R
import com.studiowash.mumong.common.adapter.AttachedImageAdapter
import com.studiowash.mumong.common.adapter.AttachedRecordingAdapter
import com.studiowash.mumong.databinding.FragmentSocialArticleBinding

class SocialArticleFragment : Fragment() {
    private lateinit var binding: FragmentSocialArticleBinding
    private val activityViewModel: SocialArticleActivityViewModel by activityViewModels()

    private val attachedRecordingAdapter = AttachedRecordingAdapter({}, {})
    private val attachedImageAdapter = AttachedImageAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSocialArticleBinding.inflate(inflater, container, false)
        initView()
        initObserve()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()
    }

    private fun initView() {
        binding.commentWriteView.setOnConfirmListener {
            Toast.makeText(context, "confirmed: $it", Toast.LENGTH_SHORT).show()
        }
    }

    private fun initToolbar() {
        binding.toolbar.setNavigationOnClickListener {
            activity?.onBackPressed()
        }

        binding.toolbar.inflateMenu(R.menu.toolbar_action_menu)
        binding.toolbar.setOnMenuItemClickListener {
            when(it.itemId) {
                R.id.action_more -> true
                else -> super.onOptionsItemSelected(it)
            }
        }
    }

    private fun initObserve() {
        activityViewModel.articleLiveData.observe(viewLifecycleOwner) {
            onUpdateArticle(it)
        }
    }

    private fun onUpdateArticle(article: SocialArticleItem?) {
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

}