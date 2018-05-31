package com.jdan.newsonline.presenter

import com.jdan.newsonline.mvp.BasePresenter

interface FMinePresenter:BasePresenter {
    fun checkCurVersion()
    fun startCollect()

}
