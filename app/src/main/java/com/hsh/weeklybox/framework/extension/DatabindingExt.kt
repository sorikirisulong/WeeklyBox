package com.hsh.weeklybox.framework.extension

import android.view.View
import androidx.databinding.BindingAdapter

object DefaultDataBindingComponent {
    @JvmStatic
    @BindingAdapter("goneUnless")
    fun goneUnless(view: View, visible: Boolean?) {
        view.visibility = if (visible == true) View.VISIBLE else View.GONE
    }
}