package com.jdan.newsonline.mvp

import android.app.Activity

interface BaseView{
    val activityContext : Activity

    fun showLoading()

    fun hideLoading()

    fun toastShow(msg: String)
    fun toastShow(msg:Int)

    fun startMainActivity()
    fun startGuideActivity()
    /**
     * 返回
     */
    fun onBackPress()
}