package com.studiowash.mumong.presentation.screen.common.search

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.annotation.AttrRes
import com.studiowash.mumong.presentation.R
import com.studiowash.mumong.presentation.databinding.ViewSearchBarBinding

class MumongSearchView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    private val binding = ViewSearchBarBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        readAttributes(attrs)
    }

    private fun readAttributes(attrs: AttributeSet?) {
        val obtainedAttributes = context.obtainStyledAttributes(attrs, R.styleable.MumongSearchView)
        try {
            hintRes = obtainedAttributes.getResourceId(R.styleable.MumongSearchView_hint_res, -1)
            editable = obtainedAttributes.getBoolean(R.styleable.MumongSearchView_editable, true)
        } finally {
            obtainedAttributes.recycle()
        }
    }

    var hintRes = R.string.default_hint
        set(value) {
            field = value
            binding.hint = context.getString(value)
        }
    var editable: Boolean = true
        set(value) {
            field = value
            binding.tvSearch.isEnabled = editable
        }
}
