package com.jdan.newsonline.presenter

import com.jdan.newsonline.mvp.BasePresenter

interface IWelcomePresenter: BasePresenter {
    fun checkCurVersion()
    /**
     * 初始化 权限
     */
    fun initPermission()

}
