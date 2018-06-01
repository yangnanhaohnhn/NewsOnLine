package com.jdan.newsonline.presenter.impl

import com.jdan.newsonline.domain.constants.Config
import com.jdan.newsonline.mvp.BaseModel
import com.jdan.newsonline.mvp.BaseModelImpl
import com.jdan.newsonline.mvp.BasePresenterImpl
import com.jdan.newsonline.presenter.ILoginPresenter
import com.jdan.newsonline.ui.view.ILoginView
import com.jdan.newsonline.util.StringUtils

class LoginPresenterImpl(view: ILoginView) : BasePresenterImpl<ILoginView,BaseModel>(),ILoginPresenter {
    override fun otherLogin(name: String) {
        if (StringUtils.isEquals(name,Config.WX)){
        }
    }

    init {
        attachView(view,BaseModelImpl())
    }
}
