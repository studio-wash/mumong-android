package com.studiowash.mumong.presentation.screen.community

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.*
import com.kakao.adfit.ads.AdListener
import com.studiowash.mumong.domain.Constants
import com.studiowash.mumong.domain.common.entity.AttachedImageEntity
import com.studiowash.mumong.domain.common.entity.CommentEntity
import com.studiowash.mumong.domain.common.entity.CommentReplyEntity
import com.studiowash.mumong.domain.common.entity.RecordingEntity
import com.studiowash.mumong.domain.community.entity.CommunityArticleEntity
import com.studiowash.mumong.domain.community.entity.CommunityTopicEntity
import com.studiowash.mumong.domain.community.entity.CommunityBoardEntity
import com.studiowash.mumong.domain.login.entity.UserEntity
import com.studiowash.mumong.presentation.R
import com.studiowash.mumong.presentation.screen.MumongFragment
import com.studiowash.mumong.presentation.screen.community.article.CommunityArticleAdapter
import com.studiowash.mumong.presentation.constant.StringKeySet
import com.studiowash.mumong.presentation.databinding.FragmentCommunityBoardBinding
import com.studiowash.mumong.presentation.widget.HorizontalDividerItemDecorator

class CommunityBoardFragment : MumongFragment(true) {
    private lateinit var binding: FragmentCommunityBoardBinding

    private val favoriteBoardAdapter = FavoriteBoardAdapter(this::onClickBoard).apply {
        favoriteBoardItems = listOf(
            CommunityBoardEntity("피아노"),
            CommunityBoardEntity("밴드"),
            CommunityBoardEntity("오케스트라")
        )
    }

    private val topicAdapter = CommunityTopicAdapter(this::onClickBest, this::onClickTopic).apply {
        topicItems = CommunityTopicEntity.values().toList()
    }

    private val recentArticleAdapter = CommunityArticleAdapter(this::onClickArticle).apply {
        recentArticleItems = listOf(
            CommunityArticleEntity(
                "이 어플 참 괜찮네요",
                "메트로놈 기능 다들 써보셨나요?\n세심하게 어플 만든 거 같아요! 다른 기능도 추가되면 좋을 것 같아요.",
                "1분 전",
                21, 2,
                listOf(
                    CommentEntity(
                        "맞아요..! 어플 너무 좋아요ㅜㅜㅜ",
                        "1분 전",
                        2,
                        listOf(),
                        UserEntity(
                            communityNickname = "비지비지",
                            profileImg = Constants.sample_image_url
                        )
                    ),
                    CommentEntity(
                        "저는 연습실 예약이 조금 어려운데, 그것도 넣으면 좋을 거 같아요! 당장 만들기에는 힘이 들겠지만..!",
                        "방금",
                        5,
                        listOf(
                            CommentReplyEntity(
                                "오 이거 진짜 좋은 거 같아요!",
                                "방금",
                                0,
                                UserEntity(
                                    communityNickname = "까지",
                                    profileImg = Constants.sample_image_url
                                )
                            ),
                            CommentReplyEntity(
                                "헐 진짜요!",
                                "방금",
                                0,
                                UserEntity(
                                    communityNickname = "비지비지",
                                    profileImg = Constants.sample_image_url
                                )
                            )
                        ),
                        UserEntity(
                            communityNickname = "까지",
                            profileImg = Constants.sample_image_url
                        )
                    ),
                    CommentEntity(
                        "맞아요..! 어플 너무 좋아요ㅜㅜㅜ",
                        "1분 전",
                        2,
                        listOf(),
                        UserEntity(
                            communityNickname = "비지비지",
                            profileImg = Constants.sample_image_url
                        )
                    )
                ),
                UserEntity(
                    communityNickname = "데이드림",
                    profileImg = Constants.sample_image_url
                ),
                attachedImages = listOf(
                    AttachedImageEntity(
                        Constants.sample_image_url
                    ),
                    AttachedImageEntity(Constants.sample_image_url)
                )
            ),
            CommunityArticleEntity(
                "오늘 연습! 한 번 평가 부탁드려요~",
                "오늘 피아노 연습 올려요! 객관적인 평가랑 피드백\n부탁드립니다. 너무 심한 말은 삼가주세 요...! 열심히 할게요.",
                "2시간 전",
                21, 2,
                listOf(),
                UserEntity(
                    communityNickname = "데샤",
                    profileImg = Constants.sample_image_url
                ),
                attachedImages = listOf(
                    AttachedImageEntity(
                        Constants.sample_image_url
                    )
                ),
                recordings = listOf(
                    RecordingEntity(
                        "",
                        "3:32",
                        "피아노",
                        "녹턴 Op.9,2번(쇼팽)"
                    )
                )
            ),
            CommunityArticleEntity(
                "같이 밴드 하실 분 모집합니다!",
                "저희 밴드 인원이 한 자리 비어서 급하게 모집합니다!\n드럼 가능하신 분으로 구하고, 못 하시더라도 친절하게 가르쳐 드려요.",
                "2시간 전",
                21, 2,
                listOf(),
                UserEntity(
                    communityNickname = "샤샤샤",
                    profileImg = Constants.sample_image_url
                )
            ),

            CommunityArticleEntity(
                "이 어플 참 괜찮네요",
                "메트로놈 기능 다들 써보셨나요?\n세심하게 어플 만든 거 같아요! 다른 기능도 추가되면 좋을 것 같아요.",
                "1분 전",
                21, 2,
                listOf(),
                UserEntity(
                    communityNickname = "데이드림",
                    profileImg = Constants.sample_image_url
                )
            ),
            CommunityArticleEntity(
                "오늘 연습! 한 번 평가 부탁드려요~",
                "오늘 피아노 연습 올려요! 객관적인 평가랑 피드백\n부탁드립니다. 너무 심한 말은 삼가주세 요...! 열심히 할게요.",
                "2시간 전",
                21, 2,
                listOf(),
                UserEntity(
                    communityNickname = "데샤",
                    profileImg = Constants.sample_image_url
                )
            ),
            CommunityArticleEntity(
                "같이 밴드 하실 분 모집합니다!",
                "저희 밴드 인원이 한 자리 비어서 급하게 모집합니다!\n드럼 가능하신 분으로 구하고, 못 하시더라도 친절하게 가르쳐 드려요.",
                "2시간 전",
                21, 2,
                listOf(),
                UserEntity(
                    communityNickname = "샤샤샤",
                    profileImg = Constants.sample_image_url
                )
            ),

            CommunityArticleEntity(
                "이 어플 참 괜찮네요",
                "메트로놈 기능 다들 써보셨나요?\n세심하게 어플 만든 거 같아요! 다른 기능도 추가되면 좋을 것 같아요.",
                "1분 전",
                21, 2,
                listOf(),
                UserEntity(
                    communityNickname = "데이드림",
                    profileImg = Constants.sample_image_url
                )
            ),
            CommunityArticleEntity(
                "오늘 연습! 한 번 평가 부탁드려요~",
                "오늘 피아노 연습 올려요! 객관적인 평가랑 피드백\n부탁드립니다. 너무 심한 말은 삼가주세 요...! 열심히 할게요.",
                "2시간 전",
                21, 2,
                listOf(),
                UserEntity(
                    communityNickname = "데샤",
                    profileImg = Constants.sample_image_url
                )
            ),
            CommunityArticleEntity(
                "같이 밴드 하실 분 모집합니다!",
                "저희 밴드 인원이 한 자리 비어서 급하게 모집합니다!\n드럼 가능하신 분으로 구하고, 못 하시더라도 친절하게 가르쳐 드려요.",
                "2시간 전",
                21, 2,
                listOf(),
                UserEntity(
                    communityNickname = "샤샤샤",
                    profileImg = Constants.sample_image_url
                )
            )
        )
    }
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCommunityBoardBinding.inflate(inflater, container, false)
        //binding.viewModel = communityViewModel

        initView()
        initOnClick()
        initObserve()

        return binding.root
    }


    private fun initView() {
        binding.ivProfile.clipToOutline = true

        binding.favoriteBoardsRecyclerView.apply {
            itemAnimator = null
            adapter = favoriteBoardAdapter
        }
        binding.topicRecyclerView.apply {
            itemAnimator = null
            adapter = topicAdapter
        }
        binding.recentArticlesRecyclerView.apply {
            adapter = recentArticleAdapter
            addItemDecoration(HorizontalDividerItemDecorator(context))
        }
        initAdfit()
    }

    private fun initOnClick() {
    }

    private fun initAdfit() {
        binding.adfitAdView.setClientId(getString(R.string.adfit_client_id_100))
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

    private fun initObserve() {
    }

    private fun onClickBoard(boardIndex: Int, board: CommunityBoardEntity) {
        favoriteBoardAdapter.selectedIndex = boardIndex
    }

    private fun onClickBest() {
        // todo
    }

    private fun onClickTopic(tagIndex: Int, tag: CommunityTopicEntity) {
        // todo
    }

    private fun onClickArticle(articleIndex: Int, article: CommunityArticleEntity) {
        val intent = Intent(context, CommunityActivity::class.java).apply {
            putExtra(StringKeySet.ARTICLE, article)
        }
        startActivity(intent)
        activity?.overridePendingTransition(R.anim.slide_in_from_right, R.anim.hold)
    }
}