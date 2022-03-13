package com.studiowash.mumong.social.friend

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import com.studiowash.mumong.R
import com.studiowash.mumong.common.model.AttachedRecordingItem
import com.studiowash.mumong.common.model.User
import com.studiowash.mumong.constant.StringKeySet
import com.studiowash.mumong.databinding.FragmentSocialHomeFriendBinding
import com.studiowash.mumong.social.article.SocialArticleActivity
import com.studiowash.mumong.social.article.SocialArticleItem

class SocialHomeFriendFragment : Fragment() {
    private val binding get() = _binding!!
    private var _binding: FragmentSocialHomeFriendBinding? = null

    private val socialHomeFriendAdapter = SocialHomeFriendAdapter(this::onClickArticle, this::onClickFriend).apply {
        friends = listOf(
            OnlineFriendItem(User(nickname = "데이드림", profileImg = "https://whoisnerdy.com/web/product/big/202201/0cb0fe62aac7685c3692371492c2cbeb.png"), true),
            OnlineFriendItem(User(nickname = "비지비지", profileImg = "https://whoisnerdy.com/web/product/big/202201/0cb0fe62aac7685c3692371492c2cbeb.png"), true),
            OnlineFriendItem(User(nickname = "까지", profileImg = "https://whoisnerdy.com/web/product/big/202201/0cb0fe62aac7685c3692371492c2cbeb.png"), true),
            OnlineFriendItem(User(nickname = "무수한 연습", profileImg = "https://whoisnerdy.com/web/product/big/202201/0cb0fe62aac7685c3692371492c2cbeb.png"), false),
            OnlineFriendItem(User(nickname = "샤샤샤", profileImg = "https://whoisnerdy.com/web/product/big/202201/0cb0fe62aac7685c3692371492c2cbeb.png"), false),
            OnlineFriendItem(User(nickname = "데이이드림", profileImg = "https://whoisnerdy.com/web/product/big/202201/0cb0fe62aac7685c3692371492c2cbeb.png"), false)
        )

        articles = listOf(
            SocialArticleItem(
                "오늘은  레슨실에서 녹턴 피아노 연습!\n통기타 2주차 연습곡으로 10월의 어느 멋진 날 연습했다! 코드 잡는 법이 아직 어렵다.",
                "1분 전",
                84, 24,
                User(nickname = "데이드림", profileImg = "https://whoisnerdy.com/web/product/big/202201/0cb0fe62aac7685c3692371492c2cbeb.png"),
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
                User(nickname = "비지비지", profileImg = "https://whoisnerdy.com/web/product/big/202201/0cb0fe62aac7685c3692371492c2cbeb.png")
            ),
            SocialArticleItem(
                "새로운 악기를 배우는 것은 언제나 즐겁다!\n오늘 처음 시작한 콘트라베이스도 굉장히 매력있는 악기인 듯 한데 아니 무슨 콘트라베이스를 갑자기 배우나요 ㅋㅋ",
                "1일 전",
                245, 121,
                User(nickname = "까지", profileImg = "https://whoisnerdy.com/web/product/big/202201/0cb0fe62aac7685c3692371492c2cbeb.png"),
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
        binding.socialHomeFriendRecyclerView.apply {
            adapter = socialHomeFriendAdapter
            itemAnimator = null
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
    }

    private fun onClickFriend(position: Int, friend: OnlineFriendItem) {
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