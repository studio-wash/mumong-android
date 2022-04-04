package com.studiowash.mumong.presentation.widget

import android.content.Context
import android.graphics.Canvas
import android.view.View
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.studiowash.mumong.R

class HorizontalDividerItemDecorator(context: Context) : ItemDecoration() {
    private val mDivider = ResourcesCompat.getDrawable(context.resources, R.drawable.horizontal_divider, context.theme)

    override fun onDrawOver(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val dividerLeft = parent.paddingLeft
        val dividerRight = parent.width - parent.paddingRight
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