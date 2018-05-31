package com.jdan.newsonline.ui.view

import android.support.v4.view.ViewPager
import com.jdan.newsonline.mvp.BaseView


interface IGuideView : BaseView {
//    fun getViewPager(): ViewPager?
    val guideVp:ViewPager
}
