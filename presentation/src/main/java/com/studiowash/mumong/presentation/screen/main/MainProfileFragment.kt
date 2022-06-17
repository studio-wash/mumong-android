package com.studiowash.mumong.presentation.screen.main

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.kakao.adfit.ads.AdListener
import com.studiowash.mumong.domain.login.LoginManager
import com.studiowash.mumong.presentation.R
import com.studiowash.mumong.presentation.databinding.FragmentMainProfileBinding
import com.studiowash.mumong.presentation.screen.MumongFragment
import com.studiowash.mumong.presentation.screen.login.LoginActivity
import com.studiowash.mumong.presentation.screen.login.LoginViewModel

class MainProfileFragment : MumongFragment(true) {
    private lateinit var binding: FragmentMainProfileBinding
    private val loginViewModel: LoginViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainProfileBinding.inflate(inflater, container, false)
        initView()
        initOnClick()
        initObserve()
        return binding.root
    }

    private fun initView() {
        binding.ivProfile.clipToOutline = true

        binding.showAlertRedDot = true
        initAdfit()
    }

    private fun initOnClick() {
        binding.cvSettingVersionInfo.root.setOnClickListener {
            AlertDialog.Builder(context)
                .setPositiveButton("ok") { p0, p1 -> loginViewModel.logout() }
                .setNegativeButton("cancel") { p0, p1 -> }
                .create().show()
        }
    }

    private fun initObserve() {
        loginViewModel.currentUser.observe(viewLifecycleOwner) {
            if (it == null) {
                // login page redirection
                val intent = Intent(activity, LoginActivity::class.java)
                startActivity(intent)
                activity?.finish()
            }
            binding.user = it
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
}