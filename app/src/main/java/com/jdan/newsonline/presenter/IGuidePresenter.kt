package com.jdan.newsonline.presenter

import android.support.v4.view.ViewPager
import com.jdan.newsonline.mvp.BasePresenter

interface IGuidePresenter : BasePresenter {
    fun loadVp()
    fun pageChangeListener(): ViewPager.OnPageChangeListener
}