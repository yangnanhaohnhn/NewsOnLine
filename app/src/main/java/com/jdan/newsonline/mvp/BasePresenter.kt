package com.jdan.newsonline.mvp

import com.jdan.newsonline.mvp.BaseModel
import com.jdan.newsonline.mvp.BaseView

interface BasePresenter{
    fun attachView(view: BaseView, model: BaseModel)

    fun detachView()
}