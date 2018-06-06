package com.jdan.newsonline.ui.activity

import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.View
import butterknife.BindView
import butterknife.OnClick
import com.jdan.newsonline.R
import com.jdan.newsonline.mvp.BaseActivity
import com.jdan.newsonline.presenter.IModifyPresenter
import com.jdan.newsonline.presenter.impl.ModifyPwdPresenterImpl
import com.jdan.newsonline.ui.view.IModifyView
import kotlinx.android.synthetic.main.activity_modify_pwd_layout.*

class ModifyPwdActivity : BaseActivity<IModifyPresenter>(), IModifyView {

    @BindView(R.id.modify_pwd_toolbar_layout) lateinit var mModifyPwdToolBar : Toolbar

    override fun createPresenter(): IModifyPresenter {
        return ModifyPwdPresenterImpl(this)
    }

    override fun getViewId(): Int {
        return R.layout.activity_modify_pwd_layout
    }

    @OnClick(R.id.modify_pwd_btn)
    override fun onClick(v: View?) {
        super.onClick(v)
        when(v!!.id){
            R.id.modify_pwd_btn->
                    mvpPresenter!!.modifyPwd()
        }
    }

    override fun initData(savedInstanceState: Bundle?) {
        initToolBar(mModifyPwdToolBar,R.string.modify_pwd)
    }

    override val oldPwdStr : String
        get() = old_pwd_et.text.toString().trim()

    override val newPwdStr : String
        get() = new_pwd_et.text.toString().trim()

    override val againNewPwdStr : String
        get() = again_new_pwd_et.text.toString().trim()
}
