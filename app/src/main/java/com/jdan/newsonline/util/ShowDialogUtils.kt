package com.jdan.newsonline.util

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.view.View
import com.jdan.newsonline.R
import com.jdan.newsonline.listener.OnClickCancelListener
import com.jdan.newsonline.widget.loading.SpotsDialog
import com.jdan.popiosdialog.IOSPromptDialog
import com.jdan.popiosdialog.OnClickConfirmListener

object ShowDialogUtils {
    fun createLoading(mContext: Context): Dialog {
        return SpotsDialog(mContext)
    }

    fun showIOSSingleNoCallback(mContext: Context, msg: String) {
        var dialog = IOSPromptDialog(mContext)
        dialog.builder().setTitle(R.string.prompt)
                .setMsg(msg)
    }

    fun showIOSSingleNoCallback(mContext: Context, msg: Int) {
        showIOSSingleNoCallback(mContext, mContext.getString(msg))
    }

    fun showIOSSingleHasCallback(mContext: Context, msg: String, listener: OnClickConfirmListener) {
        var dialog = IOSPromptDialog(mContext)
        dialog.builder().setTitle(R.string.prompt)
                .setMsg(msg)
                .setConfirmBtn(mContext.getString(R.string.ok), {
                    listener.onClickConfirm(null)
                })
    }

    fun showIOSSingleHasCallback(mContext: Context, msg: Int, listener: OnClickConfirmListener) {
        showIOSSingleHasCallback(mContext, mContext.getString(msg), listener)
    }

    fun showIOSDouble(mContext: Context, msg: Int, listener: OnClickConfirmListener) {
        showIOSDouble(mContext, mContext.getString(msg), listener)
    }

    fun showIOSDouble(mContext: Context, msg: String, listener: OnClickConfirmListener) {
        var dialog = IOSPromptDialog(mContext)
        dialog.builder().setTitle(R.string.prompt)
                .setMsg(msg)
                .setConfirmBtn(mContext.getString(R.string.OK), {
                    listener.onClickConfirm(null)
                }).setCancelBtn(mContext.getString(R.string.cancel), {
                })
    }

    fun showIOSDoubleCancelHasCallback(mContext: Context, msg: Int, listener: OnClickCancelListener) {
        showIOSDoubleCancelHasCallback(mContext, mContext.getString(msg), listener)
    }

    fun showIOSDoubleCancelHasCallback(mContext: Context, msg: String, listener: OnClickCancelListener) {
        var dialog = IOSPromptDialog(mContext)
        dialog.builder().setTitle(R.string.prompt)
                .setMsg(msg)
                .setConfirmBtn(mContext.getString(R.string.OK), {
                    listener.onClickConfirm()
                }).setCancelBtn(mContext.getString(R.string.cancel), {
                    listener.onClickCancel()
                })
    }
}