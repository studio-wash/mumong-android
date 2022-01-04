package com.studiowash.mumong_andorid

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object BindingAdapters {
    @JvmStatic
    @BindingAdapter("imageSrc")
    fun setImageSrc(imageView: ImageView, src: String) {
        Glide.with(imageView.context).load(src).into(imageView)
    }
}