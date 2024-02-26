package com.hsh.weeklybox.ui.common.helper

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.Window
import com.hsh.weeklybox.R
import java.lang.ref.WeakReference

object ProgressDialogHelper {
    private var progress: WeakReference<Dialog?>? = null
    private var isRequested = false

    fun show(context: Context?) {
        showInternal(
            context, R.layout.layout_global_loading, R.style.ProgressBarDialog,
            ColorDrawable(0), null, false
        )
    }

    fun show(context: Context?, isCancelable: Boolean, listener: DialogInterface.OnKeyListener?) {
        showInternal(
            context, R.layout.layout_global_loading, R.style.ProgressBarDialog,
            null, listener, isCancelable
        )
    }

    private fun showInternal(
        context: Context?,
        layoutResId: Int,
        styleId: Int,
        backgroundDrawable: Drawable?,
        listener: DialogInterface.OnKeyListener?,
        isCancellable: Boolean
    ) {
        if (context == null || isRequested) {
            return
        }
        try {
            val mInflater = LayoutInflater.from(context)
            @SuppressLint("InflateParams") val dialogLayout = mInflater.inflate(layoutResId, null)
            val dialog: Dialog
            dialog = if (0 != styleId) {
                Dialog(context, styleId)
            } else {
                Dialog(context)
            }
            if (null != backgroundDrawable && dialog.window != null) {
                dialog.window!!.setBackgroundDrawable(backgroundDrawable)
            }
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setContentView(dialogLayout)
            dialog.setCancelable(isCancellable)
            dialog.setOnKeyListener(listener)
            dialog.setCanceledOnTouchOutside(isCancellable)
            dialog.show()
            progress = WeakReference(dialog)
            isRequested = true
        } catch (ignore: Exception) {
            isRequested = false
            Log.e(
                "ProgressDialogHelper", "Error while displaying ProgressDialog, but ignored.")
        }
    }

    val isShowing: Boolean
        get() {
            try {
                if (progress != null && progress!!.get() != null && progress!!.get()!!
                        .isShowing
                ) {
                    return true
                }
            } catch (e: Exception) {
                Log.e("ProgressDialogHelper$e", "Fatal error in ProgressDialogHelper#isShowing")
            }
            return false
        }

    /**
     * [ProgressDialogHelper.show] 메소드로 표시한 다이얼로그를 해제합니다.
     */
    fun dismiss() {
        isRequested = false
        try {
            if (progress != null && progress!!.get() != null && progress!!.get()!!
                    .isShowing
            ) {
                progress!!.get()!!.dismiss()
                progress = null
            }
        } catch (e: Exception) {
            progress = null
            Log.e("ProgressDialogHelper$e", "Fatal error in ProgressDialogHelper#dismiss")
        }
    }
}