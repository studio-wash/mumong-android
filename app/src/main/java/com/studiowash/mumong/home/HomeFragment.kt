package com.studiowash.mumong.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.studiowash.mumong.R
import com.studiowash.mumong.databinding.FragmentHomeBinding
import com.studiowash.mumong.profile.ProfileActivity

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val noticeAdapter = NoticeAdapter()
    private val eventAdapter = EventAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        initView()
        initOnClick()

        return binding.root
    }

    private fun initView() {
        binding.noticeRecyclerView.adapter = noticeAdapter
        binding.eventRecyclerView.adapter = eventAdapter

        noticeAdapter.noticeItems = listOf(
            NoticeItem("https://whoisnerdy.com/web/product/big/202201/0cb0fe62aac7685c3692371492c2cbeb.png"),
            NoticeItem("https://whoisnerdy.com/web/product/big/202201/0cb0fe62aac7685c3692371492c2cbeb.png"),
            NoticeItem("https://whoisnerdy.com/web/product/big/202201/0cb0fe62aac7685c3692371492c2cbeb.png"),
            NoticeItem("https://whoisnerdy.com/web/product/big/202201/0cb0fe62aac7685c3692371492c2cbeb.png")
        )
        eventAdapter.eventItems = listOf(
            EventItem("https://whoisnerdy.com/web/product/big/202201/0cb0fe62aac7685c3692371492c2cbeb.png"),
            EventItem("https://whoisnerdy.com/web/product/big/202201/0cb0fe62aac7685c3692371492c2cbeb.png"),
            EventItem("https://whoisnerdy.com/web/product/big/202201/0cb0fe62aac7685c3692371492c2cbeb.png"),
            EventItem("https://whoisnerdy.com/web/product/big/202201/0cb0fe62aac7685c3692371492c2cbeb.png")
        )
    }

    private fun initOnClick() {
        binding.profileIconImageView.setOnClickListener {
            val intent = Intent(context, ProfileActivity::class.java)
            startActivity(intent)
            activity?.overridePendingTransition(R.anim.slide_in_from_right, R.anim.hold)
        }
    }
}