package com.appcent.animatedbutton

import android.graphics.Bitmap
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap

fun View.getBitmap(drawableId: Int): Bitmap {
    return ContextCompat.getDrawable(context, drawableId)!!.toBitmap()
}
