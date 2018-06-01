package com.jdan.newsonline.ui.activity

import android.os.Bundle
import com.jdan.newsonline.R
import com.jdan.newsonline.mvp.BaseActivity
import com.jdan.newsonline.presenter.IRegisterPresenter
import com.jdan.newsonline.presenter.impl.RegisterPresenterImpl
import com.jdan.newsonline.ui.view.IRegisterView

/**
 * 立即注册
 */
class RegisterActivity:BaseActivity<IRegisterPresenter>(), IRegisterView {
    override fun createPresenter(): IRegisterPresenter {
        return RegisterPresenterImpl(this)
    }

    override fun getViewId(): Int {
        return R.layout.activity_register
    }

    override fun initData(savedInstanceState: Bundle?) {
    }

}
