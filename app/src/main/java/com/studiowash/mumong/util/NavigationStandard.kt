package com.studiowash.mumong.util

import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment

fun Fragment.findNavControllerSafety(currentId: Int): NavController? {
    return try {
        NavHostFragment.findNavController(this)
    } catch (e: Exception) {
        null
    }
}