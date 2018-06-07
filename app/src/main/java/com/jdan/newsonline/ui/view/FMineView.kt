package com.jdan.newsonline.ui.view

import com.jdan.newsonline.mvp.BaseView

interface FMineView :BaseView{
    fun showUpdateTv(msg: Int)
    fun startLogin()
    fun changeTheme(isNight: Boolean)
}
