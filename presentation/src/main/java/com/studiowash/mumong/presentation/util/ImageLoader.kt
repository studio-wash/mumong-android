package com.studiowash.mumong.presentation.util

import android.graphics.drawable.Drawable
import android.widget.ImageView
import coil.load

object ImageLoader {
    fun loadImage(imageView: ImageView, uri: String?, placeholder: Drawable? = null) {
        imageView.load(uri) {
            crossfade(true)
            placeholder(placeholder)
        }
    }
}