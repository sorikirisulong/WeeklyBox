package com.hsh.weeklybox.framework.extension

import android.content.res.Resources

fun Int?.orZero(): Int {
    return this ?: 0
}

val Float.toPx: Float
    get() = (this * Resources.getSystem().displayMetrics.density)