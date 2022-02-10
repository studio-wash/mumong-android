package com.studiowash.mumong.social

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayoutMediator
import com.studiowash.mumong.R
import com.studiowash.mumong.databinding.FragmentSocialHomeBinding

class SocialHomeFragment : Fragment() {
    private lateinit var binding: FragmentSocialHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSocialHomeBinding.inflate(inflater, container, false)
        //binding.viewModel = socialViewModel

        initView()
//        initObserve()

        return binding.root
    }

    private fun initView() {
        val innerNavController = (childFragmentManager.findFragmentById(R.id.social_home_content_view) as NavHostFragment).navController
        binding.friendPageButtonLinearLayout.setOnClickListener{
            binding.pageIndex = 0
            innerNavController.navigate(R.id.socialContentFriendFragmentNav)
        }
        binding.groupPageButtonLinearLayout.setOnClickListener {
            binding.pageIndex = 1
            innerNavController.navigate(R.id.socialContentGroupFragmentNav)
        }
//        initAdfit()
    }

    private fun initAdfit() {
        // todo : move to viewpager inner fragment
//        binding.adfitAdView.setClientId(getString(R.string.adfit_client_id))
//        binding.adfitAdView.setAdListener(object : AdListener {  // optional :: 광고 수신 리스너 설정
//            override fun onAdLoaded() {
//                Log.d(tag, "onAdLoaded")
//            }
//
//            override fun onAdFailed(errorCode: Int) {
//                Log.e(tag, "onAdFailed $errorCode")
//            }
//
//            override fun onAdClicked() {
//                Log.d(tag, "onADClicked")
//            }
//        })
//
//        lifecycle.addObserver(object : LifecycleObserver {
//            // todo : deprecated
//            @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
//            fun onResume() {
//                binding.adfitAdView.resume()
//            }
//
//            @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
//            fun onPause() {
//                binding.adfitAdView.pause()
//            }
//
//            @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
//            fun onDestroy() {
//                binding.adfitAdView.destroy()
//            }
//        })
//        binding.adfitAdView.loadAd()
    }
//
//    private fun initObserve() {
//    }
//
//    private fun onClickArticle(articleIndex: Int, article: CommunityArticleItem) {
//        findNavController().navigate(
//            R.id.action_socialHomeFragmentNav_to_socialArticleFragmentNav,
//            bundleOf("ARTICLE" to article)
//        )
//    }
}