package com.jdan.newsonline.ui.activity

import android.content.Intent
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.Toolbar
import android.view.View
import butterknife.BindView
import butterknife.OnClick
import com.jdan.newsonline.R
import com.jdan.newsonline.domain.constants.Config
import com.jdan.newsonline.mvp.*
import com.jdan.newsonline.presenter.ILoginPresenter
import com.jdan.newsonline.presenter.impl.LoginPresenterImpl
import com.jdan.newsonline.ui.view.ILoginView
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.toolbar.*

/**
 * 登录页面
 */
class LoginActivity:BaseActivity<ILoginPresenter>(), ILoginView {
    override val loginConstraintLayout: ConstraintLayout
        get() = login_constraint_layout
    override val phoneEt: String
        get() = login_username_et.text.toString().trim()
    override val pwdEt: String
        get() = login_password_et.text.toString().trim()

    override fun createPresenter(): ILoginPresenter {
        return LoginPresenterImpl(this)
    }

    override fun getViewId(): Int {
        return R.layout.activity_login
    }

    @OnClick(R.id.imm_register_rl,R.id.other_login_qq_iv,R.id.other_login_wx_iv,R.id.other_login_wb_iv,R.id.login_btn)
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
            R.id.login_btn ->
                mvpPresenter!!.login()
        }
    }

    private fun startRegister() {
        var intent = Intent(activityContext,RegisterActivity::class.java)
        startActivity(intent)
    }

    override fun initData(savedInstanceState: Bundle?) {

        initToolBar(toolBar,toolbar_tv,R.string.login,true)
    }

}
