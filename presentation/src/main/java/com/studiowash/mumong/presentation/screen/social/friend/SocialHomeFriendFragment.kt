package com.studiowash.mumong.presentation.screen.social.friend

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.studiowash.mumong.domain.Constants
import com.studiowash.mumong.domain.common.entity.RecordingEntity
import com.studiowash.mumong.domain.social.entity.OnlineFriendEntity
import com.studiowash.mumong.domain.social.entity.SocialArticleEntity
import com.studiowash.mumong.domain.login.entity.UserEntity
import com.studiowash.mumong.presentation.R
import com.studiowash.mumong.presentation.screen.social.article.SocialArticleActivity
import com.studiowash.mumong.presentation.constant.StringKeySet
import com.studiowash.mumong.presentation.databinding.FragmentSocialHomeFriendBinding
import com.studiowash.mumong.presentation.widget.HorizontalDividerItemDecorator

class SocialHomeFriendFragment : Fragment() {
    private val binding get() = _binding!!
    private var _binding: FragmentSocialHomeFriendBinding? = null

    private val socialHomeFriendAdapter = SocialHomeFriendAdapter(this::onClickFriend, this::onClickArticle, this::onPlayRecording, this::onPauseRecording).apply {
        friends = listOf(
            OnlineFriendEntity(
                UserEntity(
                    nickname = "데이드림",
                    profileImg = Constants.sample_image_url
                ), true
            ),
            OnlineFriendEntity(
                UserEntity(
                    nickname = "비지비지",
                    profileImg = Constants.sample_image_url
                ), true
            ),
            OnlineFriendEntity(
                UserEntity(
                    nickname = "까지",
                    profileImg = Constants.sample_image_url
                ), true
            ),
            OnlineFriendEntity(
                UserEntity(
                    nickname = "무수한 연습",
                    profileImg = Constants.sample_image_url
                ), false
            ),
            OnlineFriendEntity(
                UserEntity(
                    nickname = "샤샤샤",
                    profileImg = Constants.sample_image_url
                ), false
            ),
            OnlineFriendEntity(
                UserEntity(
                    nickname = "데이이드림",
                    profileImg = Constants.sample_image_url
                ), false
            )
        )

        articles = listOf(
            SocialArticleEntity(
                "오늘은  레슨실에서 녹턴 피아노 연습!\n통기타 2주차 연습곡으로 10월의 어느 멋진 날 연습했다! 코드 잡는 법이 아직 어렵다.",
                "1분 전",
                84, 24,
                UserEntity(
                    nickname = "데이드림",
                    profileImg = Constants.sample_image_url
                ),
                recordings = listOf(
                    RecordingEntity(
                        "none",
                        "1:34",
                        "피아노",
                        "녹턴 Op.9-2 (쇼팽)"
                    ),
                    RecordingEntity(
                        "none",
                        "1:34",
                        "통기타",
                        "10월의 어느 멋진 날"
                    )
                )
            ),
            SocialArticleEntity(
                "바빠서 오늘 연습은 패스",
                "2시간 전",
                3, 2,
                UserEntity(
                    nickname = "비지비지",
                    profileImg = Constants.sample_image_url
                )
            ),
            SocialArticleEntity(
                "새로운 악기를 배우는 것은 언제나 즐겁다!\n오늘 처음 시작한 콘트라베이스도 굉장히 매력있는 악기인 듯 한데 아니 무슨 콘트라베이스를 갑자기 배우나요 ㅋㅋ",
                "1일 전",
                245, 121,
                UserEntity(
                    nickname = "까지",
                    profileImg = Constants.sample_image_url
                ),
                recordings = listOf(
                    RecordingEntity(
                        "none",
                        "1:34",
                        "피아노",
                        "녹턴 Op.9-(쇼팽)"
                    ),
                    RecordingEntity(
                        "none",
                        "1:34",
                        "콘트라베이스",
                        "10월의 어느 멋진 날"
                    ),
                    RecordingEntity(
                        "none",
                        "1:34",
                        "드럼",
                        "BEAT 120"
                    ),
                    RecordingEntity(
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

    private fun onClickFriend(position: Int, friend: OnlineFriendEntity) {
        val cheerFriendDialog = CheerFriendDialog.newInstance(requireContext(), friend, {
            Toast.makeText(requireContext(), "PROFILE CLICKED: FRIEND ${friend.user.nickname}", Toast.LENGTH_SHORT).show()
        }, {
            Toast.makeText(requireContext(), "CHEER CLICKED: FRIEND ${friend.user.nickname}", Toast.LENGTH_SHORT).show()
        })
        cheerFriendDialog.show()
    }

    private fun onClickArticle(position: Int, article: SocialArticleEntity) {
        val intent = Intent(context, SocialArticleActivity::class.java).apply {
            putExtra(StringKeySet.ARTICLE, article)
        }
        startActivity(intent)
        activity?.overridePendingTransition(R.anim.slide_in_from_right, R.anim.hold)
    }

    private fun onPlayRecording(recording: RecordingEntity?) {
    }

    private fun onPauseRecording(recording: RecordingEntity?) {
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}