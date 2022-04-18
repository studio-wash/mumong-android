package com.studiowash.mumong.presentation.activity.social.friend

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.studiowash.mumong.presentation.R
import com.studiowash.mumong.presentation.activity.social.article.SocialArticleActivity
import com.studiowash.mumong.presentation.constant.StringKeySet
import com.studiowash.mumong.presentation.databinding.FragmentSocialHomeFriendBinding
import com.studiowash.mumong.presentation.widget.HorizontalDividerItemDecorator

class SocialHomeFriendFragment : Fragment() {
    private val binding get() = _binding!!
    private var _binding: FragmentSocialHomeFriendBinding? = null

    private val socialHomeFriendAdapter = SocialHomeFriendAdapter(this::onClickFriend, this::onClickArticle, this::onPlayRecording, this::onPauseRecording).apply {
        friends = listOf(
            com.studiowash.mumong.domain.social.entity.OnlineFriendEntity(
                com.studiowash.mumong.domain.login.entity.UserEntity(
                    nickname = "데이드림",
                    profileImg = "https://whoisnerdy.com/web/product/big/202201/0cb0fe62aac7685c3692371492c2cbeb.png"
                ), true
            ),
            com.studiowash.mumong.domain.social.entity.OnlineFriendEntity(
                com.studiowash.mumong.domain.login.entity.UserEntity(
                    nickname = "비지비지",
                    profileImg = "https://whoisnerdy.com/web/product/big/202201/0cb0fe62aac7685c3692371492c2cbeb.png"
                ), true
            ),
            com.studiowash.mumong.domain.social.entity.OnlineFriendEntity(
                com.studiowash.mumong.domain.login.entity.UserEntity(
                    nickname = "까지",
                    profileImg = "https://whoisnerdy.com/web/product/big/202201/0cb0fe62aac7685c3692371492c2cbeb.png"
                ), true
            ),
            com.studiowash.mumong.domain.social.entity.OnlineFriendEntity(
                com.studiowash.mumong.domain.login.entity.UserEntity(
                    nickname = "무수한 연습",
                    profileImg = "https://whoisnerdy.com/web/product/big/202201/0cb0fe62aac7685c3692371492c2cbeb.png"
                ), false
            ),
            com.studiowash.mumong.domain.social.entity.OnlineFriendEntity(
                com.studiowash.mumong.domain.login.entity.UserEntity(
                    nickname = "샤샤샤",
                    profileImg = "https://whoisnerdy.com/web/product/big/202201/0cb0fe62aac7685c3692371492c2cbeb.png"
                ), false
            ),
            com.studiowash.mumong.domain.social.entity.OnlineFriendEntity(
                com.studiowash.mumong.domain.login.entity.UserEntity(
                    nickname = "데이이드림",
                    profileImg = "https://whoisnerdy.com/web/product/big/202201/0cb0fe62aac7685c3692371492c2cbeb.png"
                ), false
            )
        )

        articles = listOf(
            com.studiowash.mumong.domain.social.entity.SocialArticleEntity(
                "오늘은  레슨실에서 녹턴 피아노 연습!\n통기타 2주차 연습곡으로 10월의 어느 멋진 날 연습했다! 코드 잡는 법이 아직 어렵다.",
                "1분 전",
                84, 24,
                com.studiowash.mumong.domain.login.entity.UserEntity(
                    nickname = "데이드림",
                    profileImg = "https://whoisnerdy.com/web/product/big/202201/0cb0fe62aac7685c3692371492c2cbeb.png"
                ),
                recordings = listOf(
                    com.studiowash.mumong.domain.common.entity.RecordingEntity(
                        "none",
                        "1:34",
                        "피아노",
                        "녹턴 Op.9-2 (쇼팽)"
                    ),
                    com.studiowash.mumong.domain.common.entity.RecordingEntity(
                        "none",
                        "1:34",
                        "통기타",
                        "10월의 어느 멋진 날"
                    )
                )
            ),
            com.studiowash.mumong.domain.social.entity.SocialArticleEntity(
                "바빠서 오늘 연습은 패스",
                "2시간 전",
                3, 2,
                com.studiowash.mumong.domain.login.entity.UserEntity(
                    nickname = "비지비지",
                    profileImg = "https://whoisnerdy.com/web/product/big/202201/0cb0fe62aac7685c3692371492c2cbeb.png"
                )
            ),
            com.studiowash.mumong.domain.social.entity.SocialArticleEntity(
                "새로운 악기를 배우는 것은 언제나 즐겁다!\n오늘 처음 시작한 콘트라베이스도 굉장히 매력있는 악기인 듯 한데 아니 무슨 콘트라베이스를 갑자기 배우나요 ㅋㅋ",
                "1일 전",
                245, 121,
                com.studiowash.mumong.domain.login.entity.UserEntity(
                    nickname = "까지",
                    profileImg = "https://whoisnerdy.com/web/product/big/202201/0cb0fe62aac7685c3692371492c2cbeb.png"
                ),
                recordings = listOf(
                    com.studiowash.mumong.domain.common.entity.RecordingEntity(
                        "none",
                        "1:34",
                        "피아노",
                        "녹턴 Op.9-(쇼팽)"
                    ),
                    com.studiowash.mumong.domain.common.entity.RecordingEntity(
                        "none",
                        "1:34",
                        "콘트라베이스",
                        "10월의 어느 멋진 날"
                    ),
                    com.studiowash.mumong.domain.common.entity.RecordingEntity(
                        "none",
                        "1:34",
                        "드럼",
                        "BEAT 120"
                    ),
                    com.studiowash.mumong.domain.common.entity.RecordingEntity(
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
            addItemDecoration(HorizontalDividerItemDecorator(context))
        }
    }

    private fun onClickFriend(position: Int, friend: com.studiowash.mumong.domain.social.entity.OnlineFriendEntity) {
        // todo
    }

    private fun onClickArticle(position: Int, article: com.studiowash.mumong.domain.social.entity.SocialArticleEntity) {
        val intent = Intent(context, SocialArticleActivity::class.java).apply {
            putExtra(StringKeySet.ARTICLE, article)
        }
        startActivity(intent)
        activity?.overridePendingTransition(R.anim.slide_in_from_right, R.anim.hold)
    }

    private fun onPlayRecording(recording: com.studiowash.mumong.domain.common.entity.RecordingEntity?) {
    }

    private fun onPauseRecording(recording: com.studiowash.mumong.domain.common.entity.RecordingEntity?) {
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}