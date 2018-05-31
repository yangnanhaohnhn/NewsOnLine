package com.jdan.newsonline.ui.activity

import android.os.Bundle
import com.jdan.newsonline.R
import com.jdan.newsonline.mvp.BaseActivity
import com.jdan.newsonline.presenter.IWelcomePresenter
import com.jdan.newsonline.presenter.impl.WelcomePresenterImpl
import com.jdan.newsonline.ui.view.IWelcomeView
import com.jdan.newsonline.util.NetUtils

class WelcomeActivity : BaseActivity<IWelcomePresenter>(), IWelcomeView {

    override fun createPresenter(): IWelcomePresenter {
        return WelcomePresenterImpl(this);
    }

    override fun getViewId(): Int {
        return R.layout.activity_welcome;
    }

    override fun initData(savedInstanceState: Bundle?) {
        if (!NetUtils.isNetWorkConnected(this)){
            toastShow(R.string.isNotConnect)
            onBackPress()
        }
        //先判断当前是否更新
        setStatusBarVisible(false)
        mvpPresenter!!.initPermission()
    }

}