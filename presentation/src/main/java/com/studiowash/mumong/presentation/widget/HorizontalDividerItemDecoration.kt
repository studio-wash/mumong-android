package com.studiowash.mumong.presentation.widget

import android.content.Context
import android.graphics.Canvas
import android.view.View
import androidx.annotation.DimenRes
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.studiowash.mumong.presentation.R

class HorizontalDividerItemDecorator(context: Context, @DimenRes additionalPaddingRes: Int? = null) : ItemDecoration() {
    private val additionalPadding = if(additionalPaddingRes != null) context.resources.getDimensionPixelSize(additionalPaddingRes) else 0
    private val mDivider = ResourcesCompat.getDrawable(context.resources, R.drawable.horizontal_divider, context.theme)

    override fun onDrawOver(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val dividerLeft = parent.paddingLeft + additionalPadding
        val dividerRight = parent.width - parent.paddingRight - additionalPadding
        val childCount = parent.childCount
        for (i in 0..childCount - 2) {
            val child: View = parent.getChildAt(i)
            val params = child.layoutParams as RecyclerView.LayoutParams
            val dividerTop: Int = child.bottom + params.bottomMargin
            val dividerBottom = dividerTop + (mDivider?.intrinsicHeight ?: 0)
            mDivider?.setBounds(dividerLeft, dividerTop, dividerRight, dividerBottom)
            mDivider?.draw(canvas)
        }
    }
}