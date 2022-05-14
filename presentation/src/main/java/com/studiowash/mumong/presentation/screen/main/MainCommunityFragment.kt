package com.studiowash.mumong.presentation.screen.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.kakao.adfit.ads.AdListener
import com.studiowash.mumong.domain.community.entity.FavoriteBoardEntity
import com.studiowash.mumong.presentation.R
import com.studiowash.mumong.presentation.databinding.FragmentMainCommunityBinding
import com.studiowash.mumong.presentation.screen.MumongFragment
import com.studiowash.mumong.presentation.screen.community.FavoriteBoardAdapter

class MainCommunityFragment : MumongFragment(true) {
    private lateinit var binding: FragmentMainCommunityBinding

    private val favoriteBoardAdapter = FavoriteBoardAdapter(this::onClickBoard).apply {
        favoriteBoardItems = listOf(
            FavoriteBoardEntity("피아노"),
            FavoriteBoardEntity("밴드"),
            FavoriteBoardEntity("오케스트라")
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainCommunityBinding.inflate(inflater, container, false)
        //binding.viewModel = communityViewModel

        initView()
        initOnClick()
        initObserve()

        return binding.root
    }


    private fun initView() {
        initAdfit()
    }

    private fun initOnClick() {
    }

    private fun initAdfit() {
        val banners = listOf(binding.viewAdfitBanner1, binding.viewAdfitBanner2)
        banners.forEach { banner ->
            banner.setClientId(getString(R.string.adfit_client_id_100))
            banner.setAdListener(object :
                AdListener {  // optional :: 광고 수신 리스너 설정
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
                        Lifecycle.Event.ON_RESUME -> banner.resume()
                        Lifecycle.Event.ON_PAUSE -> banner.pause()
                        Lifecycle.Event.ON_DESTROY ->banner.destroy()
                        else -> {}
                    }
                }
            })

            banner.loadAd()
        }
    }

    private fun initObserve() {
    }

    private fun onClickBoard(boardIndex: Int, board: FavoriteBoardEntity) {
        favoriteBoardAdapter.selectedIndex = boardIndex
    }
}