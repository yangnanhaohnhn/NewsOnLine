package com.jdan.newsonline.presenter

import android.support.design.widget.BottomNavigationView
import android.support.v4.app.FragmentManager
import android.support.v4.view.ViewPager
import com.jdan.newsonline.mvp.BasePresenter

interface IMainPresenter: BasePresenter {
    fun navigationItemSelectedListener(): BottomNavigationView.OnNavigationItemSelectedListener?
    fun createFragments(supportFragmentManager: FragmentManager?)
    fun onPageChangeListener(): ViewPager.OnPageChangeListener

}
