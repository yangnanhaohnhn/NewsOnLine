package com.jdan.newsonline.presenter.impl

import com.jdan.newsonline.mvp.BaseModel
import com.jdan.newsonline.mvp.BaseModelImpl
import com.jdan.newsonline.mvp.BasePresenterImpl
import com.jdan.newsonline.presenter.ILoginPresenter
import com.jdan.newsonline.ui.view.ILoginView

class LoginPresenterImpl(view: ILoginView) : BasePresenterImpl<ILoginView,BaseModel>(),ILoginPresenter {

    init {
        attachView(view,BaseModelImpl())
    }
}
