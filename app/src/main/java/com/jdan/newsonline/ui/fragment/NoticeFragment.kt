package com.jdan.newsonline.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import com.jdan.newsonline.R
import com.jdan.newsonline.domain.constants.Config
import com.jdan.newsonline.mvp.*

/**
 * 关注fragment
 */
class NoticeFragment:BaseFragment<BasePresenter>(),BaseView {
    override fun createFPresenter(): BasePresenter {
        return BasePresenterImpl<BaseView,BaseModel>()
    }

    override fun getViewId(): Int {
        return  R.layout.activity_welcome
    }

    override fun initData(savedInstanceState: Bundle?) {
    }
    companion object {
        fun newInstance(tag:String): Fragment {
            var fragment = NoticeFragment()
            var bundle = Bundle()
            bundle.putString(Config.FRAGMENT_TAG,tag)
            fragment.arguments = bundle
            return fragment
        }
    }
}
