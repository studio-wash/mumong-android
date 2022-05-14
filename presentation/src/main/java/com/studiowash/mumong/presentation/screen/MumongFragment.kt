package com.studiowash.mumong.presentation.screen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

abstract class MumongFragment(private val needStatusBarPadding: Boolean): Fragment() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.fitsSystemWindows = true
    }
}