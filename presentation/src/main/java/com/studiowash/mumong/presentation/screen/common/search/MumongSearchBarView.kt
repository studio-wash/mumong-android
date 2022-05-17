package com.studiowash.mumong.presentation.screen.common.search

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.annotation.AttrRes
import com.studiowash.mumong.presentation.R
import com.studiowash.mumong.presentation.databinding.ViewSearchBarBinding

class MumongSearchBarView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    private val binding = ViewSearchBarBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        readAttributes(attrs)
    }

    private fun readAttributes(attrs: AttributeSet?) {
        val obtainedAttributes = context.obtainStyledAttributes(attrs, R.styleable.MumongSearchBarView)
        try {
            hintRes = obtainedAttributes.getResourceId(R.styleable.MumongSearchBarView_hint_res, -1)
            whiteMode = obtainedAttributes.getBoolean(R.styleable.MumongSearchBarView_white_mode, false)
            editable = obtainedAttributes.getBoolean(R.styleable.MumongSearchBarView_editable, true)
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
    var whiteMode: Boolean = false
        set(value) {
            field = value
            binding.ivSearch.setImageResource(
                if (value) R.drawable.ic_search_white else R.drawable.ic_search_brand
            )
        }
}
