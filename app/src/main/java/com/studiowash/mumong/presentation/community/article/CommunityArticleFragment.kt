package com.studiowash.mumong.presentation.community.article

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.kakao.adfit.ads.AdListener
import com.studiowash.mumong.R
import com.studiowash.mumong.presentation.common.adapter.AttachedImageAdapter
import com.studiowash.mumong.presentation.common.adapter.RecordingAdapter
import com.studiowash.mumong.presentation.common.adapter.CommentAdapter
import com.studiowash.mumong.databinding.FragmentCommunityArticleBinding
import com.studiowash.mumong.domain.model.community.CommunityArticleItem

class CommunityArticleFragment : Fragment() {
    private lateinit var binding: FragmentCommunityArticleBinding
    private val activityViewModel: CommunityArticleActivityViewModel by activityViewModels()

    private val attachedRecordingAdapter = RecordingAdapter({}, {})
    private val attachedImageAdapter = AttachedImageAdapter()
    private val commentAdapter = CommentAdapter({}, {})

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCommunityArticleBinding.inflate(inflater, container, false)
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

    private fun onUpdateArticle(article: CommunityArticleItem?) {
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
            adapter = attachedRecordingAdapter
            itemAnimator = null
        }
        attachedRecordingAdapter.items = article?.recordings ?: emptyList()

        binding.imageListRecyclerView.apply {
            adapter = attachedImageAdapter
            itemAnimator = null
        }
        attachedImageAdapter.items = article?.attachedImages ?: emptyList()

        binding.commentsRecyclerView.apply {
            adapter = commentAdapter
        }
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