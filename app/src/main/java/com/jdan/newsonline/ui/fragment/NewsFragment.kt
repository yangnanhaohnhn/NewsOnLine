package com.jdan.newsonline.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import com.jdan.newsonline.R
import com.jdan.newsonline.domain.constants.Config
import com.jdan.newsonline.mvp.BaseFragment
import com.jdan.newsonline.presenter.FNewsPresenter
import com.jdan.newsonline.presenter.impl.NewsPresenterImpl
import com.jdan.newsonline.ui.view.FNewsView

/**
 * 新闻fragment
 */
class NewsFragment:BaseFragment<FNewsPresenter>(), FNewsView {
    override fun initThemeMode(night: Boolean) {

    }

    override fun createFPresenter(): FNewsPresenter {
        return NewsPresenterImpl(this)
    }

    override fun getViewId(): Int {
        return R.layout.activity_welcome
    }

    override fun initData(savedInstanceState: Bundle?) {
    }
    companion object {
        fun newInstance(tag:String):Fragment{
            var fragment = NewsFragment()
            var bundle = Bundle()
            bundle.putString(Config.FRAGMENT_TAG,tag)
            fragment.arguments = bundle
            return fragment
        }
    }
}
