package com.jdan.newsonline.mvp

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife
import butterknife.Unbinder
import com.jdan.newsonline.ui.activity.GuideActivity
import com.jdan.newsonline.ui.activity.LoginActivity
import com.jdan.newsonline.ui.activity.MainActivity
import com.jdan.newsonline.util.ShowDialogUtils
import com.jdan.newsonline.util.ToastUtils
import com.orhanobut.logger.Logger

abstract class BaseFragment<FP : BasePresenter> : Fragment(), View.OnClickListener {
    protected var mvpPresenter: FP? = null
    private var dialog: Dialog? = null
    private var bind: Unbinder? = null
    private var isCreate : Boolean = false
    private var isCurFragment : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mvpPresenter = createFPresenter()
        isCreate = true
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        Logger.e("$isCreate | setUserVisibleHint | $isVisibleToUser")
        if (!isCreate){
            return
        }
        if (isVisibleToUser){
            isCreate = false
            isCurFragment = true
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(getViewId(), container, false)
        bind = ButterKnife.bind(this, view)
        Logger.e("onCreateView")
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if(isCurFragment) {
            isCurFragment = false
            initData(savedInstanceState)
        }
    }

    protected abstract fun createFPresenter(): FP

    protected abstract fun getViewId(): Int

    protected abstract fun initData(savedInstanceState: Bundle?)

    /**
     * 是否隐藏,显示
     *
     * @param view
     * @param hide
     */
    fun isHide(view: View, hide: Boolean) {
        if (hide)
            view.visibility = View.GONE
        else
            view.visibility = View.VISIBLE
    }

    fun showLoading() {
        loading()
    }

    fun hideLoading() {
        dismissLoading()
    }

    val activityContext: Activity
        get() = activity!!

    /**
     * loading...
     */
    private fun loading() {
        dialog = ShowDialogUtils.createLoading(activityContext)
        dialog!!.show()
    }

    /**
     * 关闭loading...
     */
    private fun dismissLoading() {
        if (dialog != null) {
            dialog!!.dismiss()
        }
    }

    fun toastShow(msg: String) {
        ToastUtils.show(activityContext, msg)
    }

    fun toastShow(msg: Int) {
        toastShow(getString(msg))
    }

    fun startGuideActivity() {
        var intent = Intent(activityContext, GuideActivity::class.java)
        startActivity(intent)
        onBackPress()
    }

    fun startMainActivity() {
        var intent = Intent(activityContext, MainActivity::class.java)
        startActivity(intent)
        onBackPress()
    }
    fun startLoginActivity() {
        var intent = Intent(activityContext, LoginActivity::class.java)
        startActivity(intent)
        onBackPress()
    }

    fun onBackPress() {
        activityContext.finish()
    }

    override fun onDestroy() {
        bind!!.unbind()
        if (mvpPresenter != null) {
            mvpPresenter!!.detachView()
        }
        super.onDestroy()
    }

    override fun onClick(v: View?) {
    }
}