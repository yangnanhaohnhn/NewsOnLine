package com.jdan.newsonline.presenter.impl

import com.jdan.newsonline.mvp.BaseModel
import com.jdan.newsonline.mvp.BaseModelImpl
import com.jdan.newsonline.mvp.BasePresenterImpl
import com.jdan.newsonline.presenter.FNewsPresenter
import com.jdan.newsonline.ui.view.FNewsView

class NewsPresenterImpl(view: FNewsView) : BasePresenterImpl<FNewsView,BaseModel>(),FNewsPresenter {

    init {
        attachView(view,BaseModelImpl())
    }
}
