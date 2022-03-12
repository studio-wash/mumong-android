package com.studiowash.mumong.community

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.kakao.adfit.ads.AdListener
import com.studiowash.mumong.R
import com.studiowash.mumong.common.model.AttachedImageItem
import com.studiowash.mumong.common.model.AttachedRecordingItem
import com.studiowash.mumong.common.model.CommentItem
import com.studiowash.mumong.common.model.CommentReplyItem
import com.studiowash.mumong.community.article.CommunityArticleActivity
import com.studiowash.mumong.community.article.CommunityArticleItem
import com.studiowash.mumong.databinding.FragmentCommunityHomeBinding

class CommunityHomeFragment : Fragment() {
    private lateinit var binding: FragmentCommunityHomeBinding

    private val favoriteBoardAdapter = FavoriteBoardAdapter(this::onClickBoard).apply {
        favoriteBoardItems = listOf(
            FavoriteBoardItem("피아노"),
            FavoriteBoardItem("밴드"),
            FavoriteBoardItem("오케스트라")
        )
    }

    private val tagsAdapter = TagAdapter(this::onClickTag).apply {
        tagItems = listOf(
            TagItem("자유"),
            TagItem("베스트"),
            TagItem("내 음악을 들어줘"),
            TagItem("꿀팁"),
            TagItem("질문답변"),
            TagItem("홍보"),
            TagItem("무슨 태그일까요")
        )
    }

    private val recentArticleAdapter = CommunityArticleAdapter(this::onClickArticle).apply {
        recentArticleItems = listOf(
            CommunityArticleItem(
                "이 어플 참 괜찮네요",
                "메트로놈 기능 다들 써보셨나요?\n세심하게 어플 만든 거 같아요! 다른 기능도 추가되면 좋을 것 같아요.",
                "1분 전",
                21, 2,
                listOf(
                    CommentItem(
                        "맞아요..! 어플 너무 좋아요ㅜㅜㅜ",
                        "1분 전",
                        2,
                        listOf(),
                        "비지비지",
                        "https://whoisnerdy.com/web/product/big/202201/0cb0fe62aac7685c3692371492c2cbeb.png"
                    ),
                    CommentItem(
                        "저는 연습실 예약이 조금 어려운데, 그것도 넣으면 좋을 거 같아요! 당장 만들기에는 힘이 들겠지만..!",
                        "방금",
                        5,
                        listOf(
                            CommentReplyItem(
                                "오 이거 진짜 좋은 거 같아요!",
                                "방금",
                                0,
                                "까지",
                                "https://whoisnerdy.com/web/product/big/202201/0cb0fe62aac7685c3692371492c2cbeb.png"
                            ),
                            CommentReplyItem(
                                "헐 진짜요!",
                                "방금",
                                0,
                                "비지비지",
                                "https://whoisnerdy.com/web/product/big/202201/0cb0fe62aac7685c3692371492c2cbeb.png"
                            )
                        ),
                        "까지",
                        "https://whoisnerdy.com/web/product/big/202201/0cb0fe62aac7685c3692371492c2cbeb.png"
                    ),
                    CommentItem(
                        "맞아요..! 어플 너무 좋아요ㅜㅜㅜ",
                        "1분 전",
                        2,
                        listOf(),
                        "비지비지",
                        "https://whoisnerdy.com/web/product/big/202201/0cb0fe62aac7685c3692371492c2cbeb.png"
                    )
                ),
                "데이드림",
                "https://whoisnerdy.com/web/product/big/202201/0cb0fe62aac7685c3692371492c2cbeb.png",
                attachedImages = listOf(AttachedImageItem("https://whoisnerdy.com/web/product/big/202201/0cb0fe62aac7685c3692371492c2cbeb.png"), AttachedImageItem("https://whoisnerdy.com/web/product/big/202201/0cb0fe62aac7685c3692371492c2cbeb.png"))
            ),
            CommunityArticleItem(
                "오늘 연습! 한 번 평가 부탁드려요~",
                "오늘 피아노 연습 올려요! 객관적인 평가랑 피드백\n부탁드립니다. 너무 심한 말은 삼가주세 요...! 열심히 할게요.",
                "2시간 전",
                21, 2,
                listOf(),
                "데샤",
                "https://whoisnerdy.com/web/product/big/202201/0cb0fe62aac7685c3692371492c2cbeb.png",
                attachedImages = listOf(AttachedImageItem("https://whoisnerdy.com/web/product/big/202201/0cb0fe62aac7685c3692371492c2cbeb.png")),
                attachedRecordings = listOf(AttachedRecordingItem("", "3:32", "피아노", "녹턴 Op.9,2번(쇼팽)"))
            ),
            CommunityArticleItem(
                "같이 밴드 하실 분 모집합니다!",
                "저희 밴드 인원이 한 자리 비어서 급하게 모집합니다!\n드럼 가능하신 분으로 구하고, 못 하시더라도 친절하게 가르쳐 드려요.",
                "2시간 전",
                21, 2,
                listOf(),
                "샤샤샤",
                "https://whoisnerdy.com/web/product/big/202201/0cb0fe62aac7685c3692371492c2cbeb.png"
            ),

            CommunityArticleItem(
                "이 어플 참 괜찮네요",
                "메트로놈 기능 다들 써보셨나요?\n세심하게 어플 만든 거 같아요! 다른 기능도 추가되면 좋을 것 같아요.",
                "1분 전",
                21, 2,
                listOf(),
                "데이드림",
                "https://whoisnerdy.com/web/product/big/202201/0cb0fe62aac7685c3692371492c2cbeb.png"
            ),
            CommunityArticleItem(
                "오늘 연습! 한 번 평가 부탁드려요~",
                "오늘 피아노 연습 올려요! 객관적인 평가랑 피드백\n부탁드립니다. 너무 심한 말은 삼가주세 요...! 열심히 할게요.",
                "2시간 전",
                21, 2,
                listOf(),
                "데샤",
                "https://whoisnerdy.com/web/product/big/202201/0cb0fe62aac7685c3692371492c2cbeb.png"
            ),
            CommunityArticleItem(
                "같이 밴드 하실 분 모집합니다!",
                "저희 밴드 인원이 한 자리 비어서 급하게 모집합니다!\n드럼 가능하신 분으로 구하고, 못 하시더라도 친절하게 가르쳐 드려요.",
                "2시간 전",
                21, 2,
                listOf(),
                "샤샤샤",
                "https://whoisnerdy.com/web/product/big/202201/0cb0fe62aac7685c3692371492c2cbeb.png"
            ),

            CommunityArticleItem(
                "이 어플 참 괜찮네요",
                "메트로놈 기능 다들 써보셨나요?\n세심하게 어플 만든 거 같아요! 다른 기능도 추가되면 좋을 것 같아요.",
                "1분 전",
                21, 2,
                listOf(),
                "데이드림",
                "https://whoisnerdy.com/web/product/big/202201/0cb0fe62aac7685c3692371492c2cbeb.png"
            ),
            CommunityArticleItem(
                "오늘 연습! 한 번 평가 부탁드려요~",
                "오늘 피아노 연습 올려요! 객관적인 평가랑 피드백\n부탁드립니다. 너무 심한 말은 삼가주세 요...! 열심히 할게요.",
                "2시간 전",
                21, 2,
                listOf(),
                "데샤",
                "https://whoisnerdy.com/web/product/big/202201/0cb0fe62aac7685c3692371492c2cbeb.png"
            ),
            CommunityArticleItem(
                "같이 밴드 하실 분 모집합니다!",
                "저희 밴드 인원이 한 자리 비어서 급하게 모집합니다!\n드럼 가능하신 분으로 구하고, 못 하시더라도 친절하게 가르쳐 드려요.",
                "2시간 전",
                21, 2,
                listOf(),
                "샤샤샤",
                "https://whoisnerdy.com/web/product/big/202201/0cb0fe62aac7685c3692371492c2cbeb.png"
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
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
        binding.tagRecyclerView.apply {
            adapter = tagsAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
        binding.recentArticlesRecyclerView.apply {
            adapter = recentArticleAdapter
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
        initAdfit()
    }

    private fun initOnClick() {
        binding.profileIconImageView.setOnClickListener {

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

    private fun onClickBoard(boardIndex: Int, board: FavoriteBoardItem) {
        favoriteBoardAdapter.selectedIndex = boardIndex
    }

    private fun onClickTag(tagIndex: Int, tag: TagItem) {
        // todo
    }

    private fun onClickArticle(articleIndex: Int, article: CommunityArticleItem) {
        val intent = Intent(context, CommunityArticleActivity::class.java).apply {
            putExtra("ARTICLE", article)
        }
        startActivity(intent)
        activity?.overridePendingTransition(R.anim.slide_in_from_right, R.anim.hold)
    }
}