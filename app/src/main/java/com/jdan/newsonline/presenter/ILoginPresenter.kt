package com.jdan.newsonline.presenter

import com.jdan.newsonline.mvp.BasePresenter

interface ILoginPresenter :BasePresenter{
    fun otherLogin(wx: String)
    fun login()

}
