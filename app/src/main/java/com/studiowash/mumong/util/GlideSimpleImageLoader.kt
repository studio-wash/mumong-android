package com.studiowash.mumong.util

import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide

object GlideSimpleImageLoader {
    fun loadImage(imageView: ImageView, uri: String?, placeholder: Drawable? = null) {
        Glide.with(imageView.context)
            .load(uri)
            .placeholder(placeholder)
            .into(imageView)
    }
}