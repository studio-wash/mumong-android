package com.studiowash.mumong.presentation.activity.community

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import com.kakao.adfit.ads.AdListener
import com.studiowash.mumong.presentation.activity.community.article.CommunityArticleActivity
import com.studiowash.mumong.presentation.activity.profile.ProfileActivity
import com.studiowash.mumong.presentation.R
import com.studiowash.mumong.presentation.constant.StringKeySet
import com.studiowash.mumong.presentation.constant.StringValueSet
import com.studiowash.mumong.presentation.databinding.FragmentCommunityHomeBinding
import com.studiowash.mumong.presentation.widget.HorizontalDividerItemDecorator

class CommunityHomeFragment : Fragment() {
    private lateinit var binding: FragmentCommunityHomeBinding

    private val favoriteBoardAdapter = FavoriteBoardAdapter(this::onClickBoard).apply {
        favoriteBoardItems = listOf(
            com.studiowash.mumong.domain.community.entity.FavoriteBoardEntity("피아노"),
            com.studiowash.mumong.domain.community.entity.FavoriteBoardEntity("밴드"),
            com.studiowash.mumong.domain.community.entity.FavoriteBoardEntity("오케스트라")
        )
    }

    private val topicAdapter = CommunityTopicAdapter(this::onClickBest, this::onClickTopic).apply {
        topicItems = com.studiowash.mumong.domain.community.entity.CommunityTopicEntity.values().toList()
    }

    private val recentArticleAdapter = CommunityArticleAdapter(this::onClickArticle).apply {
        recentArticleItems = listOf(
            com.studiowash.mumong.domain.community.entity.CommunityArticleEntity(
                "이 어플 참 괜찮네요",
                "메트로놈 기능 다들 써보셨나요?\n세심하게 어플 만든 거 같아요! 다른 기능도 추가되면 좋을 것 같아요.",
                "1분 전",
                21, 2,
                listOf(
                    com.studiowash.mumong.domain.common.entity.CommentEntity(
                        "맞아요..! 어플 너무 좋아요ㅜㅜㅜ",
                        "1분 전",
                        2,
                        listOf(),
                        com.studiowash.mumong.domain.login.entity.UserEntity(
                            nickname = "비지비지",
                            profileImg = "https://whoisnerdy.com/web/product/big/202201/0cb0fe62aac7685c3692371492c2cbeb.png"
                        )
                    ),
                    com.studiowash.mumong.domain.common.entity.CommentEntity(
                        "저는 연습실 예약이 조금 어려운데, 그것도 넣으면 좋을 거 같아요! 당장 만들기에는 힘이 들겠지만..!",
                        "방금",
                        5,
                        listOf(
                            com.studiowash.mumong.domain.common.entity.CommentReplyEntity(
                                "오 이거 진짜 좋은 거 같아요!",
                                "방금",
                                0,
                                com.studiowash.mumong.domain.login.entity.UserEntity(
                                    nickname = "까지",
                                    profileImg = "https://whoisnerdy.com/web/product/big/202201/0cb0fe62aac7685c3692371492c2cbeb.png"
                                )
                            ),
                            com.studiowash.mumong.domain.common.entity.CommentReplyEntity(
                                "헐 진짜요!",
                                "방금",
                                0,
                                com.studiowash.mumong.domain.login.entity.UserEntity(
                                    nickname = "비지비지",
                                    profileImg = "https://whoisnerdy.com/web/product/big/202201/0cb0fe62aac7685c3692371492c2cbeb.png"
                                )
                            )
                        ),
                        com.studiowash.mumong.domain.login.entity.UserEntity(
                            nickname = "까지",
                            profileImg = "https://whoisnerdy.com/web/product/big/202201/0cb0fe62aac7685c3692371492c2cbeb.png"
                        )
                    ),
                    com.studiowash.mumong.domain.common.entity.CommentEntity(
                        "맞아요..! 어플 너무 좋아요ㅜㅜㅜ",
                        "1분 전",
                        2,
                        listOf(),
                        com.studiowash.mumong.domain.login.entity.UserEntity(
                            nickname = "비지비지",
                            profileImg = "https://whoisnerdy.com/web/product/big/202201/0cb0fe62aac7685c3692371492c2cbeb.png"
                        )
                    )
                ),
                com.studiowash.mumong.domain.login.entity.UserEntity(
                    nickname = "데이드림",
                    profileImg = "https://whoisnerdy.com/web/product/big/202201/0cb0fe62aac7685c3692371492c2cbeb.png"
                ),
                attachedImages = listOf(
                    com.studiowash.mumong.domain.common.entity.AttachedImageEntity(
                        "https://whoisnerdy.com/web/product/big/202201/0cb0fe62aac7685c3692371492c2cbeb.png"
                    ),
                    com.studiowash.mumong.domain.common.entity.AttachedImageEntity("https://whoisnerdy.com/web/product/big/202201/0cb0fe62aac7685c3692371492c2cbeb.png")
                )
            ),
            com.studiowash.mumong.domain.community.entity.CommunityArticleEntity(
                "오늘 연습! 한 번 평가 부탁드려요~",
                "오늘 피아노 연습 올려요! 객관적인 평가랑 피드백\n부탁드립니다. 너무 심한 말은 삼가주세 요...! 열심히 할게요.",
                "2시간 전",
                21, 2,
                listOf(),
                com.studiowash.mumong.domain.login.entity.UserEntity(
                    nickname = "데샤",
                    profileImg = "https://whoisnerdy.com/web/product/big/202201/0cb0fe62aac7685c3692371492c2cbeb.png"
                ),
                attachedImages = listOf(
                    com.studiowash.mumong.domain.common.entity.AttachedImageEntity(
                        "https://whoisnerdy.com/web/product/big/202201/0cb0fe62aac7685c3692371492c2cbeb.png"
                    )
                ),
                recordings = listOf(
                    com.studiowash.mumong.domain.common.entity.RecordingEntity(
                        "",
                        "3:32",
                        "피아노",
                        "녹턴 Op.9,2번(쇼팽)"
                    )
                )
            ),
            com.studiowash.mumong.domain.community.entity.CommunityArticleEntity(
                "같이 밴드 하실 분 모집합니다!",
                "저희 밴드 인원이 한 자리 비어서 급하게 모집합니다!\n드럼 가능하신 분으로 구하고, 못 하시더라도 친절하게 가르쳐 드려요.",
                "2시간 전",
                21, 2,
                listOf(),
                com.studiowash.mumong.domain.login.entity.UserEntity(
                    nickname = "샤샤샤",
                    profileImg = "https://whoisnerdy.com/web/product/big/202201/0cb0fe62aac7685c3692371492c2cbeb.png"
                )
            ),

            com.studiowash.mumong.domain.community.entity.CommunityArticleEntity(
                "이 어플 참 괜찮네요",
                "메트로놈 기능 다들 써보셨나요?\n세심하게 어플 만든 거 같아요! 다른 기능도 추가되면 좋을 것 같아요.",
                "1분 전",
                21, 2,
                listOf(),
                com.studiowash.mumong.domain.login.entity.UserEntity(
                    nickname = "데이드림",
                    profileImg = "https://whoisnerdy.com/web/product/big/202201/0cb0fe62aac7685c3692371492c2cbeb.png"
                )
            ),
            com.studiowash.mumong.domain.community.entity.CommunityArticleEntity(
                "오늘 연습! 한 번 평가 부탁드려요~",
                "오늘 피아노 연습 올려요! 객관적인 평가랑 피드백\n부탁드립니다. 너무 심한 말은 삼가주세 요...! 열심히 할게요.",
                "2시간 전",
                21, 2,
                listOf(),
                com.studiowash.mumong.domain.login.entity.UserEntity(
                    nickname = "데샤",
                    profileImg = "https://whoisnerdy.com/web/product/big/202201/0cb0fe62aac7685c3692371492c2cbeb.png"
                )
            ),
            com.studiowash.mumong.domain.community.entity.CommunityArticleEntity(
                "같이 밴드 하실 분 모집합니다!",
                "저희 밴드 인원이 한 자리 비어서 급하게 모집합니다!\n드럼 가능하신 분으로 구하고, 못 하시더라도 친절하게 가르쳐 드려요.",
                "2시간 전",
                21, 2,
                listOf(),
                com.studiowash.mumong.domain.login.entity.UserEntity(
                    nickname = "샤샤샤",
                    profileImg = "https://whoisnerdy.com/web/product/big/202201/0cb0fe62aac7685c3692371492c2cbeb.png"
                )
            ),

            com.studiowash.mumong.domain.community.entity.CommunityArticleEntity(
                "이 어플 참 괜찮네요",
                "메트로놈 기능 다들 써보셨나요?\n세심하게 어플 만든 거 같아요! 다른 기능도 추가되면 좋을 것 같아요.",
                "1분 전",
                21, 2,
                listOf(),
                com.studiowash.mumong.domain.login.entity.UserEntity(
                    nickname = "데이드림",
                    profileImg = "https://whoisnerdy.com/web/product/big/202201/0cb0fe62aac7685c3692371492c2cbeb.png"
                )
            ),
            com.studiowash.mumong.domain.community.entity.CommunityArticleEntity(
                "오늘 연습! 한 번 평가 부탁드려요~",
                "오늘 피아노 연습 올려요! 객관적인 평가랑 피드백\n부탁드립니다. 너무 심한 말은 삼가주세 요...! 열심히 할게요.",
                "2시간 전",
                21, 2,
                listOf(),
                com.studiowash.mumong.domain.login.entity.UserEntity(
                    nickname = "데샤",
                    profileImg = "https://whoisnerdy.com/web/product/big/202201/0cb0fe62aac7685c3692371492c2cbeb.png"
                )
            ),
            com.studiowash.mumong.domain.community.entity.CommunityArticleEntity(
                "같이 밴드 하실 분 모집합니다!",
                "저희 밴드 인원이 한 자리 비어서 급하게 모집합니다!\n드럼 가능하신 분으로 구하고, 못 하시더라도 친절하게 가르쳐 드려요.",
                "2시간 전",
                21, 2,
                listOf(),
                com.studiowash.mumong.domain.login.entity.UserEntity(
                    nickname = "샤샤샤",
                    profileImg = "https://whoisnerdy.com/web/product/big/202201/0cb0fe62aac7685c3692371492c2cbeb.png"
                )
            )
        )
    }
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCommunityHomeBinding.inflate(inflater, container, false)
        //binding.viewModel = communityViewModel

        initView()
        initOnClick()
        initObserve()

        return binding.root
    }


    private fun initView() {
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
        binding.profileIconImageView.setOnClickListener {
            val intent = Intent(context, ProfileActivity::class.java)
            intent.putExtra(StringKeySet.CATEGORY, StringValueSet.COMMUNITY)
            startActivity(intent)
            activity?.overridePendingTransition(R.anim.slide_in_from_right, R.anim.hold)
        }
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

    private fun onClickBoard(boardIndex: Int, board: com.studiowash.mumong.domain.community.entity.FavoriteBoardEntity) {
        favoriteBoardAdapter.selectedIndex = boardIndex
    }

    private fun onClickBest() {
        // todo
    }

    private fun onClickTopic(tagIndex: Int, tag: com.studiowash.mumong.domain.community.entity.CommunityTopicEntity) {
        // todo
    }

    private fun onClickArticle(articleIndex: Int, article: com.studiowash.mumong.domain.community.entity.CommunityArticleEntity) {
        val intent = Intent(context, CommunityArticleActivity::class.java).apply {
            putExtra(StringKeySet.ARTICLE, article)
        }
        startActivity(intent)
        activity?.overridePendingTransition(R.anim.slide_in_from_right, R.anim.hold)
    }
}