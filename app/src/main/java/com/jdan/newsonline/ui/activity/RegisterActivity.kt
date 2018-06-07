package com.jdan.newsonline.ui.activity

import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.design.widget.TextInputLayout
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import butterknife.BindView
import butterknife.OnClick
import cn.smssdk.SMSSDK
import com.jdan.newsonline.R
import com.jdan.newsonline.mvp.BaseActivity
import com.jdan.newsonline.presenter.IRegisterPresenter
import com.jdan.newsonline.presenter.impl.RegisterPresenterImpl
import com.jdan.newsonline.ui.view.IRegisterView
import kotlinx.android.synthetic.main.toolbar.*

/**
 * 立即注册
 */
class RegisterActivity:BaseActivity<IRegisterPresenter>(), IRegisterView {



    @BindView(R.id.register_constraint_layout) lateinit var mRegisterConstraintLayout: ConstraintLayout
    @BindView(R.id.register_phone_et) lateinit var mRegisterPhoneEt : EditText
    @BindView(R.id.register_code_et) lateinit var mRegisterCodeEt : EditText

    @BindView(R.id.get_phone_code_btn) lateinit var mGetPhoneCodeBtn : Button

    override fun createPresenter(): IRegisterPresenter {
        return RegisterPresenterImpl(this)
    }

    override fun getViewId(): Int {
        return R.layout.activity_register
    }

    @OnClick(R.id.get_phone_code_btn, R.id.enter_news_btn)
    override fun onClick(v: View?) {
        super.onClick(v)
        when(v!!.id){
            R.id.get_phone_code_btn ->
                //获取验证码
                mvpPresenter!!.getPhoneCode()
            R.id.enter_news_btn ->
                mvpPresenter!!.enterNews()
                //进入新闻
        }
    }

    override fun initData(savedInstanceState: Bundle?) {
        initToolBar(toolBar,toolbar_tv,R.string.register,true)
    }

    override val mRegisterPhone: String
        get() = mRegisterPhoneEt.text.toString().trim()

    override val registerConstraintLayout: ConstraintLayout
        get() = mRegisterConstraintLayout

    override val phoneCodeBtn: Button
        get() = mGetPhoneCodeBtn

    override val phoneCodeStr: String
        get() = mRegisterCodeEt.text.toString().trim()

    override fun onDestroy() {
        //用完回调要注销掉，否则可能会出现内存泄露
        SMSSDK.unregisterAllEventHandler()
        super.onDestroy()
    }

}
