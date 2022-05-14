package com.studiowash.mumong.presentation.screen.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.kakao.adfit.ads.AdListener
import com.studiowash.mumong.domain.community.entity.CommunityBoardEntity
import com.studiowash.mumong.domain.login.LoginStatus
import com.studiowash.mumong.presentation.R
import com.studiowash.mumong.presentation.constant.StringKeySet
import com.studiowash.mumong.presentation.databinding.FragmentMainCommunityBinding
import com.studiowash.mumong.presentation.screen.MumongFragment
import com.studiowash.mumong.presentation.screen.community.CommunityActivity
import com.studiowash.mumong.presentation.screen.community.board.CommunityAllBoardAdapter
import com.studiowash.mumong.presentation.screen.community.board.CommunityFavoriteBoardAdapter
import com.studiowash.mumong.presentation.screen.community.search.CommunitySearchActivity
import com.studiowash.mumong.presentation.widget.HorizontalDividerItemDecorator

class MainCommunityFragment : MumongFragment(true) {
    private val binding get() = _binding!!
    private var _binding: FragmentMainCommunityBinding? = null

    private val favoriteBoardAdapter = CommunityFavoriteBoardAdapter({ position, board -> }, this::onClickBoard).apply {
        boardItems = listOf(
            CommunityBoardEntity("피아노 게시판",
                hasNewArticle = true,
                isFavorite = true,
                recentArticleContent = "이 어플 참 괜찮네요를레히히히히깔…"
            ),
            CommunityBoardEntity("오케스트라 게시판",
                hasNewArticle = true,
                isFavorite = true,
                recentArticleContent = "이 어플 참 괜찮네요를레히히히히깔…"
            ),
            CommunityBoardEntity("밴드 게시판",
                hasNewArticle = true,
                isFavorite = true,
                recentArticleContent = "이 어플 참 괜찮네요를레히히히히깔…"
            ),
            CommunityBoardEntity("현악기 게시판",
                hasNewArticle = true,
                isFavorite = false,
                recentArticleContent = "이 어플 참 괜찮네요를레히히히히깔…"
            )
        )
    }
    private val allBoardAdapter = CommunityAllBoardAdapter({ position, board -> }, this::onClickBoard).apply {
        boardItems = listOf(
            CommunityBoardEntity("피아노 게시판",
                hasNewArticle = true,
                isFavorite = true
            ),
            CommunityBoardEntity("기타 게시판",
                hasNewArticle = true,
                isFavorite = true
            ),
            CommunityBoardEntity("오케스트라 게시판",
                hasNewArticle = false,
                isFavorite = true
            ),
            CommunityBoardEntity("밴드 게시판",
                hasNewArticle = true,
                isFavorite = false
            ),
            CommunityBoardEntity("현악기 게시판",
                hasNewArticle = true,
                isFavorite = false
            ),
            CommunityBoardEntity("목관악기 게시판",
                hasNewArticle = true,
                isFavorite = false
            ),
            CommunityBoardEntity("타악기 게시판",
                hasNewArticle = false,
                isFavorite = false
            ),
            CommunityBoardEntity("건반악기 게시판",
                hasNewArticle = false,
                isFavorite = false
            ),
            CommunityBoardEntity("작곡 게시판",
                hasNewArticle = false,
                isFavorite = false
            ),
            CommunityBoardEntity("국악 게시판",
                hasNewArticle = false,
                isFavorite = false
            ),
            CommunityBoardEntity("금관악기 게시판",
                hasNewArticle = false,
                isFavorite = false
            ),
            CommunityBoardEntity("보컬 게시판",
                hasNewArticle = true,
                isFavorite = false
            )
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        if (_binding == null) {
            _binding = FragmentMainCommunityBinding.inflate(inflater, container, false)
            //binding.viewModel = communityViewModel

            initView()
            initOnClick()
            initObserve()

        }
        return binding.root
    }


    private fun initView() {
        binding.communityNickname = LoginStatus.currentUser?.communityNickname
        binding.rvFavoriteBoards.apply {
            itemAnimator = null
            addItemDecoration(HorizontalDividerItemDecorator(context, R.dimen.fragment_default_horizontal_padding))
            adapter = favoriteBoardAdapter
        }
        binding.rvAllBoards.apply {
            itemAnimator = null
            adapter = allBoardAdapter
        }
        initAdfit()
    }

    private fun initOnClick() {
        binding.svSearchBoard.setOnClickListener {
            val intent = Intent(context, CommunitySearchActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initAdfit() {
        val banners = listOf(binding.viewAdfitBanner1, binding.viewAdfitBanner2)
        banners.forEach { banner ->
            banner.setClientId(getString(R.string.adfit_client_id_100))
            banner.setAdListener(object :
                AdListener {  // optional :: 광고 수신 리스너 설정
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
                        Lifecycle.Event.ON_RESUME -> banner.resume()
                        Lifecycle.Event.ON_PAUSE -> banner.pause()
                        Lifecycle.Event.ON_DESTROY ->banner.destroy()
                        else -> {}
                    }
                }
            })

            banner.loadAd()
        }
    }

    private fun initObserve() {
    }

    private fun onClickBoard(boardIndex: Int, board: CommunityBoardEntity) {
        val intent = Intent(context, CommunityActivity::class.java).apply {
            putExtra(StringKeySet.BOARD, board)
        }
        startActivity(intent)
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}