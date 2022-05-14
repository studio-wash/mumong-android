package com.studiowash.mumong.presentation.screen.common.comment

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.annotation.AttrRes
import androidx.core.widget.doAfterTextChanged
import com.studiowash.mumong.presentation.databinding.ViewCommentWriteBinding

class CommentWriteView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    private val binding = ViewCommentWriteBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        binding.commentEditText.doAfterTextChanged {
            binding.isValidComment = it?.isNotBlank() == true
        }
    }

    fun setOnConfirmListener(onConfirm: (comment: String) -> Unit) {
        binding.commentConfirmImageView.setOnClickListener {
            onConfirm.invoke(binding.commentEditText.text.toString())
        }
    }
}
