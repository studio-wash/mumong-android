package com.studiowash.mumong.util

import android.widget.ImageView
import com.bumptech.glide.Glide

object GlideSimpleImageLoader {
    fun loadImage(imageView: ImageView, uri: String?) {
        Glide.with(imageView.context)
            .load(uri)
            .into(imageView)
    }
}