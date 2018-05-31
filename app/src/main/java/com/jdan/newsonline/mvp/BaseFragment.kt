package com.jdan.newsonline.mvp

import android.app.Activity
import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife
import butterknife.Unbinder
import com.jdan.newsonline.util.ShowDialogUtils
import com.jdan.newsonline.util.ToastUtils

abstract class BaseFragment<FP : BasePresenter> : Fragment(), View.OnClickListener {
    protected var mvpPresenter: FP? = null
    private var dialog: Dialog? = null
    private var bind: Unbinder? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mvpPresenter = createFPresenter()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(getViewId(), container, false)
        bind = ButterKnife.bind(this, view)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initData(savedInstanceState)
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
    }

    fun startMainActivity() {
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