package com.studiowash.mumong.presentation.util

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes

object ToastUtil {
    fun showToast(context: Context, message: String) = Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    fun showToast(context: Context, @StringRes messageRes: Int) = Toast.makeText(context, messageRes, Toast.LENGTH_SHORT).show()
}