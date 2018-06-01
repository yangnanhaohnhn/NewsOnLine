package com.jdan.newsonline.presenter.impl

import com.jdan.newsonline.mvp.BaseModel
import com.jdan.newsonline.mvp.BaseModelImpl
import com.jdan.newsonline.mvp.BasePresenterImpl
import com.jdan.newsonline.presenter.IRegisterPresenter
import com.jdan.newsonline.ui.view.IRegisterView

class RegisterPresenterImpl(view: IRegisterView) :BasePresenterImpl<IRegisterView,BaseModel>(), IRegisterPresenter {

    init {
        attachView(view,BaseModelImpl())
    }
}
