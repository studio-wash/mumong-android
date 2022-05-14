package com.studiowash.mumong.presentation.screen.social.article

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.studiowash.mumong.presentation.R
import com.studiowash.mumong.presentation.screen.MumongFragment
import com.studiowash.mumong.presentation.screen.common.attach.AttachedImageAdapter
import com.studiowash.mumong.presentation.screen.common.attach.RecordingAdapter
import com.studiowash.mumong.presentation.databinding.FragmentSocialArticleBinding

class SocialArticleFragment : MumongFragment(true) {
    private lateinit var binding: FragmentSocialArticleBinding
    private val activityViewModel: SocialArticleActivityViewModel by activityViewModels()

    private val attachedRecordingAdapter = RecordingAdapter({}, {})
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
        binding.ivUserProfile.clipToOutline = true

        binding.commentWriteView.setOnConfirmListener {
            Toast.makeText(context, "confirmed: $it", Toast.LENGTH_SHORT).show()
        }
    }

    private fun initToolbar() {
        binding.toolbar.setNavigationOnClickListener {
            activity?.onBackPressed()
        }

        binding.toolbar.inflateMenu(R.menu.toolbar_action_menu_article)
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

    private fun onUpdateArticle(article: com.studiowash.mumong.domain.social.entity.SocialArticleEntity?) {
        binding.item = article

        // todo : viewmodel로 추후 이동
        // SERVER API : 좋아요, 북마크 결과 받아서 ui에 적용 가능한 api
        binding.llLike.setOnClickListener {
            binding.isLiked = binding.isLiked.not()
        }
        binding.llScrap.setOnClickListener {
            binding.isBookmarked = binding.isBookmarked.not()
        }

        binding.recordListRecyclerView.apply {
            adapter = attachedRecordingAdapter
            itemAnimator = null
        }
        attachedRecordingAdapter.items = article?.recordings ?: emptyList()

        binding.rvImageList.apply {
            adapter = attachedImageAdapter
            itemAnimator = null
        }
        attachedImageAdapter.items = article?.attachedImages ?: emptyList()
    }

}