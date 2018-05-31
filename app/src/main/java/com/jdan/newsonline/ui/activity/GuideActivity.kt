package com.jdan.newsonline.ui.activity

import android.os.Bundle
import android.support.v4.view.ViewPager
import butterknife.BindView
import com.jdan.newsonline.R
import com.jdan.newsonline.mvp.BaseActivity
import com.jdan.newsonline.presenter.IGuidePresenter
import com.jdan.newsonline.presenter.impl.GuidePresenterImpl
import com.jdan.newsonline.ui.view.IGuideView

class GuideActivity : BaseActivity<IGuidePresenter>(), IGuideView {


    @BindView(R.id.guide_vp) lateinit var mGuideVp:ViewPager

    override fun createPresenter(): IGuidePresenter {
        return GuidePresenterImpl(this)
    }

    override fun getViewId(): Int {
        return R.layout.activity_guide
    }

    override fun initData(savedInstanceState: Bundle?) {
        setStatusBarVisible(false)

        mvpPresenter!!.loadVp()
        mGuideVp.addOnPageChangeListener(mvpPresenter!!.pageChangeListener())
    }

    override val guideVp: ViewPager
        get() = mGuideVp
}
