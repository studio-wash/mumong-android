package com.studiowash.mumong

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.studiowash.mumong.util.GlideSimpleImageLoader

object BindingAdapters {
    @JvmStatic
    @BindingAdapter("imageSrc")
    fun setImageSrc(imageView: ImageView, src: String?) {
        GlideSimpleImageLoader.loadImage(imageView, src)
    }

    @JvmStatic
    @BindingAdapter("goneUnless")
    fun setGoneUnless(view: View, condition: Boolean) {
        view.visibility = if (condition) View.VISIBLE else View.GONE
    }

    @JvmStatic
    @BindingAdapter("invisibleUnless")
    fun setInvisibleUnless(view: View, condition: Boolean) {
        view.visibility = if (condition) View.VISIBLE else View.INVISIBLE
    }

    @JvmStatic
    @BindingAdapter("isSelected")
    fun setIsSelected(view: View, selected: Boolean) {
        view.isSelected = selected
    }

    @JvmStatic
    @BindingAdapter("isActivated")
    fun setIsActivated(view: View, activated: Boolean) {
        view.isActivated = activated
    }
}