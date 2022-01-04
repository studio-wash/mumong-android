package com.studiowash.mumong_andorid.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.studiowash.mumong_andorid.databinding.FragmentHomeBinding

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

        return binding.root
    }

    private fun initView() {
        binding.noticeRecyclerView.apply {
            adapter = noticeAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
        binding.eventRecyclerView.apply {
            adapter = eventAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }

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
}