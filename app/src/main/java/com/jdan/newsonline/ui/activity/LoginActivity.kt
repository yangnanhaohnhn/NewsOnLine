package com.jdan.newsonline.ui.activity

import android.os.Bundle
import com.jdan.newsonline.R
import com.jdan.newsonline.mvp.BaseActivity
import com.jdan.newsonline.presenter.ILoginPresenter
import com.jdan.newsonline.presenter.impl.LoginPresenterImpl
import com.jdan.newsonline.ui.view.ILoginView

/**
 * 登录页面
 */
class LoginActivity:BaseActivity<ILoginPresenter>(), ILoginView {
    override fun createPresenter(): ILoginPresenter {
        return LoginPresenterImpl(this)
    }

    override fun getViewId(): Int {
        return R.layout.activity_login
    }

    override fun initData(savedInstanceState: Bundle?) {
    }

}
