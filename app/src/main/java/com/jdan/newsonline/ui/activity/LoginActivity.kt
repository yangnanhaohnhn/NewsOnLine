package com.jdan.newsonline.ui.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.View
import butterknife.BindView
import butterknife.OnClick
import com.jdan.newsonline.R
import com.jdan.newsonline.domain.constants.Config
import com.jdan.newsonline.mvp.BaseActivity
import com.jdan.newsonline.presenter.ILoginPresenter
import com.jdan.newsonline.presenter.impl.LoginPresenterImpl
import com.jdan.newsonline.ui.view.ILoginView

/**
 * 登录页面
 */
class LoginActivity:BaseActivity<ILoginPresenter>(), ILoginView {
    @BindView(R.id.appToolBar) lateinit var toolbar: Toolbar
    override fun createPresenter(): ILoginPresenter {
        return LoginPresenterImpl(this)
    }

    override fun getViewId(): Int {
        return R.layout.activity_login
    }

    @OnClick(R.id.imm_register_rl,R.id.other_login_qq_iv,R.id.other_login_wx_iv,R.id.other_login_wb_iv)
    override fun onClick(v: View?) {
        super.onClick(v)
        when(v!!.id){
            R.id.imm_register_rl ->
                    //注册
                startRegister()
            R.id.other_login_wx_iv ->
                mvpPresenter!!.otherLogin(Config.WX)
            R.id.other_login_qq_iv ->
                mvpPresenter!!.otherLogin(Config.QQ)
            R.id.other_login_wb_iv ->
                mvpPresenter!!.otherLogin(Config.WB)
        }
    }

    private fun startRegister() {
        var intent = Intent(activityContext,RegisterActivity::class.java)
        startActivity(intent)
    }

    override fun initData(savedInstanceState: Bundle?) {
        initToolBar(toolbar,R.string.login)

    }

}
