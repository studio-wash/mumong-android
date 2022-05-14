package com.studiowash.mumong.presentation.screen.community.article

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.fragment.navArgs
import com.kakao.adfit.ads.AdListener
import com.studiowash.mumong.presentation.R
import com.studiowash.mumong.presentation.databinding.FragmentCommunityArticleBinding
import com.studiowash.mumong.presentation.screen.MumongFragment
import com.studiowash.mumong.presentation.screen.common.attach.AttachedImageAdapter
import com.studiowash.mumong.presentation.screen.common.attach.RecordingAdapter
import com.studiowash.mumong.presentation.widget.HorizontalDividerItemDecorator
import com.studiowash.mumong.presentation.widget.VerticalSpacingItemDecoration

class CommunityArticleFragment : MumongFragment(true) {
    private lateinit var binding: FragmentCommunityArticleBinding
    private val activityViewModel: CommunityArticleViewModel by viewModels()

    private val attachedRecordingAdapter = RecordingAdapter({}, {})
    private val attachedImageAdapter = AttachedImageAdapter()
    private val commentAdapter = CommunityCommentAdapter({}, {})

    private val args: CommunityArticleFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCommunityArticleBinding.inflate(inflater, container, false)
        initView()
        initObserve()
        initData()
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

        binding.rvRecordList.apply {
            adapter = attachedRecordingAdapter
            itemAnimator = null
        }

        binding.rvImageList.apply {
            adapter = attachedImageAdapter
            itemAnimator = null
            val spacing = resources.getDimensionPixelSize(R.dimen.rv_vertical_spacing_height)
            addItemDecoration(VerticalSpacingItemDecoration(spacing))
        }

        binding.commentsRecyclerView.apply {
            adapter = commentAdapter
            addItemDecoration(HorizontalDividerItemDecorator(context))
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

    private fun initData() {
        activityViewModel.setArticle(args.article)
    }

    private fun onUpdateArticle(article: com.studiowash.mumong.domain.community.entity.CommunityArticleEntity?) {
        binding.item = article

        // todo : viewmodel로 추후 이동
        // SERVER API : 좋아요, 북마크 결과 받아서 ui에 적용 가능한 api
        binding.llLike.setOnClickListener {
            binding.isLiked = binding.isLiked.not()
        }
        binding.bookmarkButtonLinearLayout.setOnClickListener {
            binding.isBookmarked = binding.isBookmarked.not()
        }

        attachedRecordingAdapter.items = article?.recordings ?: emptyList()
        attachedImageAdapter.items = article?.attachedImages ?: emptyList()
        commentAdapter.items = article?.comments ?: emptyList()

        initAdfit()
    }

    private fun initAdfit() {
        binding.adfitAdView.setClientId(getString(R.string.adfit_client_id_50))
        binding.adfitAdView.setAdListener(object : AdListener {  // optional :: 광고 수신 리스너 설정
            override fun onAdLoaded() {
                Log.d(tag, "onAdLoaded")
            }

            override fun onAdFailed(errorCode: Int) {
                Log.e(tag, "onAdFailed $errorCode")
            }

            override fun onAdClicked() {
                Log.d(tag, "onADClicked")
            }
        })

        lifecycle.addObserver(object : LifecycleEventObserver {
            override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
                when (event) {
                    Lifecycle.Event.ON_RESUME -> binding.adfitAdView.resume()
                    Lifecycle.Event.ON_PAUSE -> binding.adfitAdView.pause()
                    Lifecycle.Event.ON_DESTROY ->binding.adfitAdView.destroy()
                    else -> {}
                }
            }
        })

        binding.adfitAdView.loadAd()
    }
}