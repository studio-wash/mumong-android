package com.studiowash.mumong.presentation.activity.practice.diary.view.calendar

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import androidx.annotation.AttrRes
import androidx.core.content.res.ResourcesCompat
import com.studiowash.mumong.presentation.R

class ColorPercentageRectView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val path = Path()
    private val paint = Paint()
    private val backgroundRect = RectF()
    private val additionalRect = RectF()

    var innerPadding =
        context.resources.getDimensionPixelSize(R.dimen._calendar_date_background_inner_padding)
            .toFloat()
    var radius =
        context.resources.getDimensionPixelSize(R.dimen._calendar_date_background_radius).toFloat()

    var percent = 1.3f
    var paintColor = ResourcesCompat.getColor(
        context.resources,
        R.color._calendar_date_default_color,
        context.theme
    )

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        paint.color = paintColor
        paint.alpha = (255f * SINGLE_FULL_COLOR_PERCENT).toInt()

        val fullCount = percent.toInt()
        val additionalPercent = percent - fullCount

        // background (full)
        backgroundRect.set(
            innerPadding,
            innerPadding,
            measuredWidth.toFloat() - innerPadding,
            measuredHeight.toFloat() - innerPadding
        )

        repeat(fullCount) {
            canvas?.drawRoundRect(backgroundRect, radius, radius, paint)
        }

        additionalRect.set(
            innerPadding,
            (measuredHeight.toFloat() - 2 * innerPadding) * (1f - additionalPercent) + innerPadding,
            measuredWidth.toFloat() - innerPadding,
            measuredHeight.toFloat() - innerPadding
        )

        // additional (not full)
        val corners = floatArrayOf(
            0f, 0f, // Top left radius in px
            0f, 0f,  // Top right radius in px
            radius, radius, // Bottom right radius in px
            radius, radius, // Bottom left radius in px
        )

        path.reset()
        path.addRoundRect(additionalRect, corners, Path.Direction.CW)
        canvas?.drawPath(path, paint)
    }

    companion object {
        private const val SINGLE_FULL_COLOR_PERCENT = 0.2f
    }
}