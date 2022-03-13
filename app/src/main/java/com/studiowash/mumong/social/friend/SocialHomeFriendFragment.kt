package com.studiowash.mumong.social.friend

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.studiowash.mumong.R
import com.studiowash.mumong.common.model.AttachedRecordingItem
import com.studiowash.mumong.const.StringKeySet
import com.studiowash.mumong.databinding.FragmentSocialHomeFriendBinding
import com.studiowash.mumong.social.article.SocialArticleActivity
import com.studiowash.mumong.social.article.SocialArticleItem

class SocialHomeFriendFragment : Fragment() {
    private val binding get() = _binding!!
    private var _binding: FragmentSocialHomeFriendBinding? = null

    private val onlineFriendAdapter = OnlineFriendAdapter(this::onClickOnlineFriend).apply {
        items = listOf(
            OnlineFriendItem("데이드림", "https://whoisnerdy.com/web/product/big/202201/0cb0fe62aac7685c3692371492c2cbeb.png", true),
            OnlineFriendItem("비지비지", "https://whoisnerdy.com/web/product/big/202201/0cb0fe62aac7685c3692371492c2cbeb.png", true),
            OnlineFriendItem("까지", "https://whoisnerdy.com/web/product/big/202201/0cb0fe62aac7685c3692371492c2cbeb.png", true),
            OnlineFriendItem("무수한 연습", "https://whoisnerdy.com/web/product/big/202201/0cb0fe62aac7685c3692371492c2cbeb.png", false),
            OnlineFriendItem("샤샤샤", "https://whoisnerdy.com/web/product/big/202201/0cb0fe62aac7685c3692371492c2cbeb.png", false),
            OnlineFriendItem("데이이드림", "https://whoisnerdy.com/web/product/big/202201/0cb0fe62aac7685c3692371492c2cbeb.png", false)
        )
    }

    private val articleAdapter = SocialFriendArticleAdapter(this::onClickArticle).apply {
        items = listOf(
            SocialArticleItem(
                "오늘은  레슨실에서 녹턴 피아노 연습!\n통기타 2주차 연습곡으로 10월의 어느 멋진 날 연습했다! 코드 잡는 법이 아직 어렵다.",
                "1분 전",
                84, 24,
                "데이드림",
                "https://whoisnerdy.com/web/product/big/202201/0cb0fe62aac7685c3692371492c2cbeb.png",
                attachedRecordings = listOf(
                    AttachedRecordingItem(
                        "none",
                        "1:34",
                        "피아노",
                        "녹턴 Op.9-2 (쇼팽)"
                    ),
                    AttachedRecordingItem(
                        "none",
                        "1:34",
                        "통기타",
                        "10월의 어느 멋진 날"
                    )
                )
            ),
            SocialArticleItem(
                "바빠서 오늘 연습은 패스",
                "2시간 전",
                3, 2,
                "비지비지",
                "https://whoisnerdy.com/web/product/big/202201/0cb0fe62aac7685c3692371492c2cbeb.png"
            ),
            SocialArticleItem(
                "새로운 악기를 배우는 것은 언제나 즐겁다!\n오늘 처음 시작한 콘트라베이스도 굉장히 매력있는 악기인 듯 한데 아니 무슨 콘트라베이스를 갑자기 배우나요 ㅋㅋ",
                "1일 전",
                245, 121,
                "까지",
                "https://whoisnerdy.com/web/product/big/202201/0cb0fe62aac7685c3692371492c2cbeb.png",
                attachedRecordings = listOf(
                    AttachedRecordingItem(
                        "none",
                        "1:34",
                        "피아노",
                        "녹턴 Op.9-(쇼팽)"
                    ),
                    AttachedRecordingItem(
                        "none",
                        "1:34",
                        "콘트라베이스",
                        "10월의 어느 멋진 날"
                    ),
                    AttachedRecordingItem(
                        "none",
                        "1:34",
                        "드럼",
                        "BEAT 120"
                    ),
                    AttachedRecordingItem(
                        "none",
                        "1:34",
                        "통기타",
                        "10월의 어느 멋진 날"
                    )
                )
            ),
        )
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSocialHomeFriendBinding.inflate(inflater, container, false)
        initView()
        return binding.root
    }

    private fun initView() {
        binding.onlineFriendsRecyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = onlineFriendAdapter
        }
        binding.friendArticlesRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = articleAdapter
            itemAnimator = null
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
    }

    private fun onClickOnlineFriend(position: Int, friend: OnlineFriendItem) {
        // todo
    }

    private fun onClickArticle(position: Int, article: SocialArticleItem) {
        val intent = Intent(context, SocialArticleActivity::class.java).apply {
            putExtra(StringKeySet.ARTICLE, article)
        }
        startActivity(intent)
        activity?.overridePendingTransition(R.anim.slide_in_from_right, R.anim.hold)
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}