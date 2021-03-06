package com.jdan.newsonline.ui.activity

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.view.ViewPager
import android.view.KeyEvent
import butterknife.BindView
import com.jdan.newsonline.R
import com.jdan.newsonline.mvp.BaseActivity
import com.jdan.newsonline.presenter.IMainPresenter
import com.jdan.newsonline.presenter.impl.MainPresenterImpl
import com.jdan.newsonline.ui.view.IMainView
import com.jdan.newsonline.util.BottomNavigationViewHelper

class MainActivity: BaseActivity<IMainPresenter>(), IMainView {
    override fun startModifyPwdActivity() {
        var intent = Intent(activityContext,ModifyPwdActivity::class.java)
        startActivity(intent)
    }

    @BindView(R.id.main_vp) lateinit var mMainVp : ViewPager
    @BindView(R.id.main_navigation) lateinit var mMainNavigation : BottomNavigationView
    override fun createPresenter(): IMainPresenter{
        return MainPresenterImpl(this)
    }

    override fun getViewId(): Int {
        return R.layout.activity_main
    }

    override fun initData(savedInstanceState: Bundle?) {
        //检验当前密码是否是初始化密码
        mvpPresenter!!.checkCurPwd()

        mMainNavigation.menu.getItem(0).isChecked = true
        //设置fragment
        mvpPresenter!!.createFragments(supportFragmentManager)

        //设置导航栏
        mMainNavigation.setOnNavigationItemSelectedListener(mvpPresenter!!.navigationItemSelectedListener())
        BottomNavigationViewHelper.disableShiftMode(mMainNavigation)
        //设置监听
        mMainVp.addOnPageChangeListener(mvpPresenter!!.onPageChangeListener())
    }

    override val mainVp: ViewPager
        get() = mMainVp

    override val mainNavigation: BottomNavigationView
        get() = mMainNavigation

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        return backPressed(keyCode,event)
    }

}