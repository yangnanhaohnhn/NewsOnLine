package com.jdan.newsonline.application

import android.content.Context
import android.support.multidex.MultiDexApplication
import android.view.WindowManager
import com.jdan.newsonline.util.AppUtils

open class NOLApplication : MultiDexApplication(){

    private var SCREEN_WIDTH = 0
    private var SCREEN_HEIGHT = 0
    // 屏幕密度
    private var SCREEN_DENSITY: Float = 0.toFloat()


    override fun onCreate() {
        super.onCreate()

        mContext = this
        AppUtils.init(this)

        getScreenSize()
    }

    //用这个当成普通静态方法
    companion object {
        private var mContext: NOLApplication? = null
        fun getInstance(): NOLApplication {
            if (null == mContext) {
                mContext = NOLApplication()
            }
            return mContext as NOLApplication
        }
    }

    fun getContext(): Context {
        return mContext as Context
    }

    fun getWidth(): Int {
        return SCREEN_WIDTH
    }

    fun getHeight(): Int {
        return SCREEN_HEIGHT
    }

    private fun getScreenSize() {
        var wm = applicationContext
                .getSystemService(Context.WINDOW_SERVICE) as WindowManager
        var display = wm.defaultDisplay

        SCREEN_WIDTH = display.width
        SCREEN_HEIGHT = display.height
        val displayMetrics = resources.displayMetrics
        SCREEN_DENSITY = displayMetrics.density
    }
}