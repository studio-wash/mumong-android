package com.studiowash.mumong.presentation.activity

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.studiowash.mumong.presentation.util.statusBarHeight

abstract class MumongFragment(private val needStatusBarPadding: Boolean): Fragment() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.setPadding(0, if (needStatusBarPadding) context?.statusBarHeight()?:0 else 0, 0, 0)
    }
}