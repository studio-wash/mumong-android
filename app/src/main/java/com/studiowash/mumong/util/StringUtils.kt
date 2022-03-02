package com.studiowash.mumong.util

import android.content.Context
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ImageSpan
import androidx.core.content.res.ResourcesCompat
import com.studiowash.mumong.R

object StringUtils {
    fun getWithRecordingMicIcon(context: Context, text: String) : SpannableStringBuilder {
        val ssb = SpannableStringBuilder(text)
        val drawable = ResourcesCompat.getDrawable(context.resources, R.drawable.ic_attached_recording_mic, context.theme)
        if (drawable != null) {
            drawable.setBounds(0,0,45,60)
            val spaceString = " "
            ssb.append(spaceString)
            val drawableString = "M"
            ssb.append(drawableString)
            ssb.setSpan(
                ImageSpan(drawable),
                ssb.length - drawableString.length,
                ssb.length,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }
        return ssb
    }
}