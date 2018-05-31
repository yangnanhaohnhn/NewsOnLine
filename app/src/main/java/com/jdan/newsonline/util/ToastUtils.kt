package com.jdan.newsonline.util

import android.content.Context
import android.os.SystemClock
import android.widget.Toast
import com.jdan.newsonline.util.ThreadManager

/**
 * ToastUtils
 *
 */
object ToastUtils {

    init {
        throw AssertionError()
    }

    private var toast: Toast? = null


    fun show(context: Context, resId: Int) {
        show(context, context.resources.getText(resId), Toast.LENGTH_SHORT)
    }

    fun show(context: Context, resId: Int, duration: Int) {
        show(context, context.resources.getText(resId), duration)
    }

    @JvmOverloads
    fun show(context: Context, text: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
        synchronized(context) {
            if (toast != null && toast!!.duration == duration) {
                toast!!.setText(text)
            } else {
                toast = Toast.makeText(context.applicationContext, text, duration)
            }
            toast!!.show()
        }
        ThreadManager.getSinglePool().execute(Runnable {
            SystemClock.sleep(3000)
            synchronized(context) {
                toast = null
            }
        })
    }

    fun show(context: Context, resId: Int, vararg args: Any) {
        show(context, String.format(context.resources.getString(resId), *args), Toast.LENGTH_SHORT)
    }

    fun show(context: Context, format: String, vararg args: Any) {
        show(context, String.format(format, *args), Toast.LENGTH_SHORT)
    }

    fun show(context: Context, resId: Int, duration: Int, vararg args: Any) {
        show(context, String.format(context.resources.getString(resId), *args), duration)
    }

    fun show(context: Context, format: String, duration: Int, vararg args: Any) {
        show(context, String.format(format, *args), duration)
    }
}
