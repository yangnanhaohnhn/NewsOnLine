package com.jdan.newsonline.util

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import com.jdan.newsonline.widget.loading.SpotsDialog

object ShowDialogUtils {
    fun createLoading(mContext: Context) : Dialog {
        return SpotsDialog(mContext)
    }
}