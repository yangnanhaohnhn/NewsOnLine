package com.jdan.newsonline.ui.view

import android.support.design.widget.BottomNavigationView
import android.support.v4.view.ViewPager
import com.jdan.newsonline.mvp.BaseView
import com.jdan.newsonline.widget.viewpager.LazyViewPager

interface IMainView : BaseView {
    val mainVp: ViewPager
    val mainNavigation: BottomNavigationView

}
