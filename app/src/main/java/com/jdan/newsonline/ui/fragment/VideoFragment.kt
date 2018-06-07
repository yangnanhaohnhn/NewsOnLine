package com.jdan.newsonline.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import com.jdan.newsonline.R
import com.jdan.newsonline.domain.constants.Config
import com.jdan.newsonline.mvp.*

/**
 * 视频fragment
 */
class VideoFragment:BaseFragment<BasePresenter>(), BaseView {
    override fun initThemeMode(night: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun createFPresenter(): BasePresenter {
        return BasePresenterImpl<BaseView,BaseModel>()
    }

    override fun getViewId(): Int {
        return R.layout.activity_main
    }

    override fun initData(savedInstanceState: Bundle?) {
    }
    companion object {
        fun newInstance(tag:String): Fragment {
            var fragment = VideoFragment()
            var bundle = Bundle()
            bundle.putString(Config.FRAGMENT_TAG,tag)
            fragment.arguments = bundle
            return fragment
        }
    }
}
