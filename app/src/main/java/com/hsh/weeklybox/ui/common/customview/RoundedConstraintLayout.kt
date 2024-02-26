package com.hsh.weeklybox.ui.common.customview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import android.util.AttributeSet
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.hsh.weeklybox.R
import com.hsh.weeklybox.framework.extension.toPx

open class RoundedConstraintLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    enum class ExcludeCorner(val id: Int) {
        NONE(0),
        ONLY_TOP(1),
        ONLY_BOTTOM(2);

        companion object {
            fun fromId(id: Int): ExcludeCorner {
                return values().firstOrNull { it.id == id } ?: NONE
            }
        }
    }

    var radius = 0f
    var filledColor: Int = Color.TRANSPARENT
    private var strokedColor: Int = Color.TRANSPARENT
    private var excludeCorner = ExcludeCorner.NONE
    private var strokedWidth: Float = 1.5f.toPx

    init {
        attrs?.let {
            context.obtainStyledAttributes(it, R.styleable.RoundedConstraintLayout).apply {
                radius = getDimension(R.styleable.RoundedConstraintLayout_rc_radius, 4f.toPx)
                filledColor = getColor(R.styleable.RoundedConstraintLayout_rc_fill_color, Color.TRANSPARENT)
                strokedColor = getColor(R.styleable.RoundedConstraintLayout_rc_stroke_color, Color.TRANSPARENT)
                strokedWidth = getDimension(R.styleable.RoundedConstraintLayout_rc_stroke_width, 1f.toPx)
                excludeCorner = ExcludeCorner.fromId(getInt(R.styleable.RoundedConstraintLayout_exclude_corner, 0))
            }.recycle()
        }
        setWillNotDraw(false)
    }

    fun setFillColor(@ColorRes colorId: Int) {
        filledColor = ContextCompat.getColor(context, colorId)
        invalidate()
    }

    fun setFillColorInt(@ColorInt colorInt: Int) {
        filledColor = colorInt
        invalidate()
    }

    fun setCornerRadius(radius: Float) {
        this.radius = radius.toPx
        invalidate()
    }

    fun setStrokeWidth(width: Float) {
        this.strokedWidth = width
        invalidate()
    }

    fun setStrokeColor(@ColorInt colorInt: Int) {
        this.strokedColor = colorInt
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        var path: Path
        when (excludeCorner) {
            ExcludeCorner.NONE -> {
                path = Path().apply {
                    addRoundRect(RectF(0f, 0f, canvas?.width?.toFloat()
                        ?: 0F, canvas?.height?.toFloat()
                        ?: 0F), radius, radius, Path.Direction.CW)
                }
            }
            ExcludeCorner.ONLY_TOP -> {
                val corners = floatArrayOf(
                    radius, radius,
                    radius, radius,
                    0f, 0f,
                    0f, 0f,
                )

                path = Path().apply {
                    addRoundRect(RectF(0f, 0f, canvas?.width?.toFloat()
                        ?: 0F, canvas?.height?.toFloat()
                        ?: 0F), corners, Path.Direction.CW)
                }
            }
            ExcludeCorner.ONLY_BOTTOM -> {
                val corners = floatArrayOf(
                    0f, 0f,
                    0f, 0f,
                    radius, radius,
                    radius, radius,
                )

                path = Path().apply {
                    addRoundRect(RectF(0f, 0f, canvas?.width?.toFloat()
                        ?: 0F, canvas?.height?.toFloat()
                        ?: 0F), corners, Path.Direction.CW)
                }
            }
        }

        val paint = Paint().apply {
            color = filledColor
            style = Paint.Style.FILL
        }
        val strokePaint = Paint().apply {
            color = strokedColor
            strokeWidth = strokedWidth
            style = Paint.Style.STROKE
        }
        val strokeRect = RectF().apply {
            set(0f, 0f, canvas?.width?.toFloat()
                ?: 0F, canvas?.height?.toFloat()
                ?: 0F)
            inset(strokePaint.strokeWidth / 2, strokePaint.strokeWidth / 2)
        }

        canvas?.drawPath(path!!, paint)
        canvas?.drawRoundRect(strokeRect, radius, radius, strokePaint)
        canvas?.clipPath(path!!)
        super.onDraw(canvas)
    }
}