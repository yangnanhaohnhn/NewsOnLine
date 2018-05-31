package com.jdan.newsonline.mvp

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.View
import butterknife.ButterKnife
import butterknife.Unbinder
import com.jdan.newsonline.ui.activity.GuideActivity
import com.jdan.newsonline.ui.activity.LoginActivity
import com.jdan.newsonline.ui.activity.MainActivity
import com.jdan.newsonline.util.ShowDialogUtils
import com.jdan.newsonline.util.ToastUtils

abstract class BaseActivity<P : BasePresenter> : AppCompatActivity(), View.OnClickListener {

    protected var mvpPresenter: P? = null
    private var dialog : Dialog? = null
    private var bind : Unbinder? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getViewId())
        mvpPresenter = createPresenter()
        bind = ButterKnife.bind(this)
        setStatusBarVisible(true)
        initData(savedInstanceState)
    }

    /**
     * 显示和隐藏 状态栏
     */
    fun setStatusBarVisible(isShow: Boolean) {
        if (isShow) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        } else {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_FULLSCREEN
        }
    }

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

    protected abstract fun createPresenter(): P

    protected abstract fun getViewId(): Int

    protected abstract fun initData(savedInstanceState: Bundle?)

    fun showLoading() {
        loading()
    }

    fun hideLoading() {
        dismissLoading()
    }

    val activityContext: Activity
        get() = this

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

    fun onBackPress() {
        finish()
    }

    override fun onClick(v: View?) {
    }

    override fun getResources(): Resources {
        val res = super.getResources()
        val config = Configuration()
        config.setToDefaults()
        res.updateConfiguration(config, res.displayMetrics)
        return res
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

    override fun onDestroy() {
        bind!!.unbind()
        if (mvpPresenter != null) {
            mvpPresenter!!.detachView()
        }

        super.onDestroy()
    }

}