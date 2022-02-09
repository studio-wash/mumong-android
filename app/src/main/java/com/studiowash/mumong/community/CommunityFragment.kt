package com.studiowash.mumong.community

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.studiowash.mumong.R
import com.studiowash.mumong.databinding.FragmentCommunityBinding

class CommunityFragment : Fragment() {
    private lateinit var binding: FragmentCommunityBinding

    private val favoriteBoardAdapter = FavoriteBoardAdapter().apply {
        favoriteBoardItems = listOf(
            FavoriteBoardItem("피아노"),
            FavoriteBoardItem("밴드"),
            FavoriteBoardItem("오케스트라")
        )
    }

    private val mumongPickArticleAdapter = MumongPickArticleAdapter().apply {
        mumongPickArticleItems = listOf(
            MumongPickArticleItem("추천", "피아노를 공부하는 루틴", "데이드림", "https://whoisnerdy.com/web/product/big/202201/0cb0fe62aac7685c3692371492c2cbeb.png"),
            MumongPickArticleItem("공지사항", "깨끗한 커뮤니티를 만들자", "뮤몽 운영진", "https://whoisnerdy.com/web/product/big/202201/0cb0fe62aac7685c3692371492c2cbeb.png"),
            MumongPickArticleItem("공지사항", "깨끗한 커뮤니티를 만들자", "뮤몽 운영진", "https://whoisnerdy.com/web/product/big/202201/0cb0fe62aac7685c3692371492c2cbeb.png")
        )
    }

    private val recentArticleAdapter = RecentArticlesAdapter().apply {
        recentArticleItems = listOf(
            RecentArticleItem(
                "이 어플 참 괜찮네요",
                "메트로놈 기능 다들 써보셨나요?\n세심하게 어플 만든 거 같아요! 다른 기능도 추가되면 좋을 것 같아요.",
                "1분 전",
                21, 2,
                "데이드림",
                "https://whoisnerdy.com/web/product/big/202201/0cb0fe62aac7685c3692371492c2cbeb.png"
            ),
            RecentArticleItem(
                "오늘 연습! 한 번 평가 부탁드려요~",
                "오늘 피아노 연습 올려요! 객관적인 평가랑 피드백\n부탁드립니다. 너무 심한 말은 삼가주세 요...! 열심히 할게요.",
                "2시간 전",
                21, 2,
                "데샤",
                "https://whoisnerdy.com/web/product/big/202201/0cb0fe62aac7685c3692371492c2cbeb.png"
            ),
            RecentArticleItem(
                "같이 밴드 하실 분 모집합니다!",
                "저희 밴드 인원이 한 자리 비어서 급하게 모집합니다!\n드럼 가능하신 분으로 구하고, 못 하시더라도 친절하게 가르쳐 드려요.",
                "2시간 전",
                21, 2,
                "샤샤샤",
                "https://whoisnerdy.com/web/product/big/202201/0cb0fe62aac7685c3692371492c2cbeb.png"
            )
        )
    }
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCommunityBinding.inflate(inflater, container, false)
        //binding.viewModel = communityViewModel

        initView()
        initObserve()

        return binding.root
    }

    private fun initView() {
        binding.favoriteBoardsRecyclerView.apply {
            adapter = favoriteBoardAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
        binding.mumongPickRecyclerView.apply {
            adapter = mumongPickArticleAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
        binding.recentArticlesRecyclerView.apply {
            adapter = recentArticleAdapter
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
    }

    private fun initObserve() {
    }
}