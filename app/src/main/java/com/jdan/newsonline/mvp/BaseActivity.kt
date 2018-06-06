package com.jdan.newsonline.mvp

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.KeyEvent
import android.view.View
import android.widget.TextView
import android.widget.Toast
import butterknife.ButterKnife
import butterknife.Unbinder
import com.jdan.newsonline.R
import com.jdan.newsonline.ui.activity.GuideActivity
import com.jdan.newsonline.ui.activity.LoginActivity
import com.jdan.newsonline.ui.activity.MainActivity
import com.jdan.newsonline.util.ShowDialogUtils
import com.jdan.newsonline.util.ToastUtils

abstract class BaseActivity<P : BasePresenter> : AppCompatActivity(), View.OnClickListener {

    protected var mvpPresenter: P? = null
    private var dialog : Dialog? = null
    private var bind : Unbinder? = null
    private var exitTimeMillis: Long = 0

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



   private fun initToolBar(toolBar:Toolbar){
        setSupportActionBar(toolBar)
        if(supportActionBar != null){
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            supportActionBar!!.setDisplayShowTitleEnabled(false)
        }
       toolBar.setNavigationOnClickListener {
           onBackPress()
       }
    }

    fun initToolBar(toolBar: Toolbar,title:String) : Toolbar{
        initToolBar(toolBar)
        var bar_title = toolBar.findViewById<TextView>(R.id.toolbar_tv)
        bar_title.text = title
        return toolBar
    }
    fun initToolBar(toolBar: Toolbar,title:Int) : Toolbar{
        return initToolBar(toolBar,getString(title))
    }

    fun backPressed(keyCode:Int,event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event!!.action == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTimeMillis) > 2000) {
                Toast.makeText(activityContext,R.string.click_again_to_exit,Toast.LENGTH_SHORT).show()
                exitTimeMillis = System.currentTimeMillis()
            } else {
//                sharedUtil.putBoolean(Constant.ISEXITAPP, true);
//                PoteApplication.getInstance().finishActivityList();
                finish()
                System.exit(0)
            }
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

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