package com.appcent.animatedbutton

import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View
import androidx.core.animation.doOnEnd
import androidx.core.animation.doOnStart
import androidx.core.content.ContextCompat
import androidx.core.graphics.withScale
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import com.google.android.material.math.MathUtils.lerp

class LikeButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : View(context, attrs, defStyle) {

    private var fraction: Float = 0f

    private val iconBitmap: Bitmap

    private var iconWidth: Int
    private var iconHeight: Int

    private val radius = 32 * resources.displayMetrics.density

    private val paint = TextPaint(Paint.ANTI_ALIAS_FLAG).apply {
        textAlign = Paint.Align.LEFT
    }

    // Initialize color variables with default values or values from attributes
    private var unlikeBackgroundColor = ContextCompat.getColor(context, R.color.unlike_background_color)
    private var backgroundColor = ContextCompat.getColor(context, R.color.like_background_color)
    private var likeBackgroundColor = ContextCompat.getColor(context, R.color.like_background_color)
    private var unlikeImageColor = ContextCompat.getColor(context, R.color.unlike_image_color)
    private var imageColor = ContextCompat.getColor(context, R.color.like_image_color)
    private var likeImageColor = ContextCompat.getColor(context, R.color.like_image_color)

    private var loopLike: Boolean = false
    private var uiState: UIState = UIState.UnLike

    init {
        // Obtain attribute values if provided in XML layout
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.LikeButton)
        backgroundColor = typedArray.getColor(R.styleable.LikeButton_likeBackgroundColor, backgroundColor)
        imageColor = typedArray.getColor(R.styleable.LikeButton_likeImageColor, imageColor)
        likeBackgroundColor = typedArray.getColor(R.styleable.LikeButton_likeBackgroundColor, backgroundColor)
        likeImageColor = typedArray.getColor(R.styleable.LikeButton_likeImageColor, imageColor)
        unlikeBackgroundColor = typedArray.getColor(R.styleable.LikeButton_unlikeBackgroundColor, unlikeBackgroundColor)
        unlikeImageColor = typedArray.getColor(R.styleable.LikeButton_unlikeImageColor, unlikeImageColor)
        val iconDrawableResId = typedArray.getResourceId(
            R.styleable.LikeButton_iconDrawable,
            R.drawable.ic_baseline_add_smile
        )
        iconWidth = typedArray.getDimensionPixelSize(R.styleable.LikeButton_iconWidth, 64.dpToPx())
        iconHeight = typedArray.getDimensionPixelSize(R.styleable.LikeButton_iconHeight, 64.dpToPx())
        iconBitmap = scaleBitmap(getBitmap(iconDrawableResId), iconWidth, iconHeight)
        loopLike = typedArray.getBoolean(R.styleable.LikeButton_loopLike, false)
        typedArray.recycle()
    }

    private val srcInMode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
    private val drawableCanvas = Canvas(this.iconBitmap)
    private val argbEvaluator = ArgbEvaluator()
    private fun getRoundRectPaint() = paint.apply {
        val backgroundFraction = (fraction * 2).coerceAtMost(1f)
        color = argbEvaluator.evaluate(backgroundFraction, unlikeBackgroundColor, backgroundColor) as Int
    }

    private fun Int.dpToPx(): Int {
        return (this * resources.displayMetrics.density).toInt()
    }

    private fun scaleBitmap(bitmap: Bitmap, width: Int, height: Int): Bitmap {
        val scaledBitmap = Bitmap.createScaledBitmap(bitmap, width, height, true)
        if (scaledBitmap != bitmap) {
            bitmap.recycle()
        }
        return scaledBitmap
    }

    private fun getBitmapPaint() = paint.apply {
        val backgroundFraction = (fraction * 2).coerceAtMost(1f)
        color = argbEvaluator.evaluate(backgroundFraction, unlikeImageColor, imageColor) as Int
    }

    private fun tintBitmap() {
        paint.xfermode = srcInMode
        drawableCanvas.drawRect(0f, 0f, this.iconBitmap.width.toFloat(), this.iconBitmap.height.toFloat(), getBitmapPaint())
        paint.xfermode = null
    }

    private fun updateBackgroundColor(uiState: UIState) {
        if (uiState == UIState.UnLike) {
            backgroundColor = unlikeBackgroundColor
            imageColor = unlikeImageColor
        } else {
            backgroundColor = likeBackgroundColor
            imageColor = likeImageColor
        }
    }

    override fun onDraw(canvas: Canvas) {
        // round rect
        getRoundRectPaint().also {
            // bound
            val left = 0f
            val top = 0f
            val right = width.toFloat()
            val bottom = height.toFloat()
            // scale
            val scaleX = lerp(1f, 0.9f, 1f, fraction)
            val scaleY = scaleX
            val pivotX = width / 2f
            val pivotY = height / 2f
            canvas.withScale(scaleX, scaleY, pivotX, pivotY) {
                canvas.drawRoundRect(left, top, right, bottom, radius, radius, it)
            }
        }

        // heart icon
        getBitmapPaint().also { paint ->
            val scaleX = lerp(1f, 0.6f, 1f, fraction)
            val scaleY = scaleX
            val pivotX = width / 2f
            val pivotY = height / 2f
            canvas.withScale(scaleX, scaleY, pivotX, pivotY) {
                val left = width / 2f - iconBitmap.width / 2f
                val top = height / 2f - iconBitmap.height / 2f
                tintBitmap()
                canvas.drawBitmap(iconBitmap, left, top, paint)
            }
        }
    }

    private fun runAnimation(): ValueAnimator {
        return ValueAnimator.ofFloat(0f, 1f).apply {
            addUpdateListener {
                fraction = it.animatedValue as Float
                invalidate()
            }
            interpolator = FastOutSlowInInterpolator()
            doOnStart {
                this@LikeButton.uiState = UIState.Animating
            }
            duration = 500L
            start()
        }
    }

    private fun lerp(a: Float, b: Float, c: Float, fraction: Float): Float {
        return if (fraction <= 0.5f) {
            lerp(a, b, fraction * 2)
        } else {
            val tempFraction = fraction - 0.5f
            lerp(b, c, tempFraction * 2)
        }
    }

    fun toggleUIState() {
        if (uiState == UIState.Like) {
            setUIState(UIState.UnLike, true)
        } else {
            setUIState(UIState.Like, true)
        }
    }

    private fun setUIState(uiState: UIState, isAnim: Boolean) {
        updateBackgroundColor(uiState)

        if (isAnim) {
            runAnimation().apply {
                doOnEnd {
                    this@LikeButton.uiState = uiState
                }
            }.start() // Start the animation explicitly
        } else {
            this.uiState = uiState
            fraction = 0f
            invalidate()
        }
    }
}