package com.jdan.newsonline.ui.fragment

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import butterknife.OnClick
import com.jdan.newsonline.R
import com.jdan.newsonline.domain.constants.Config
import com.jdan.newsonline.mvp.BaseActivity
import com.jdan.newsonline.mvp.BaseFragment
import com.jdan.newsonline.mvp.BasePresenter
import com.jdan.newsonline.presenter.FMinePresenter
import com.jdan.newsonline.presenter.impl.MinePresenterImpl
import com.jdan.newsonline.ui.activity.LoginActivity
import com.jdan.newsonline.ui.view.FMineView
import com.jdan.newsonline.util.ThemeManager
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.fragment_mine.*

/**
 * 我的 fragment
 */
class MineFragment : BaseFragment<FMinePresenter>(), FMineView, ThemeManager.OnThemeChangeListener {

    override fun onThemeChanged() {
        initTheme()
    }

    fun initTheme() {
// 设置标题栏颜色
        // 设置状态栏颜色
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            Window window = getWindow();
//            window.setStatusBarColor(getResources().getColor(ThemeManager.getCurrentThemeRes(MainActivity.this, R.color.colorPrimary)));
//        }
        mine_constraint_layout.setBackgroundColor(resources.getColor(ThemeManager.getCurThemeRe(activityContext,R.color.backgroundColor_night)))
    }

    override fun showUpdateTv(msg: Int) {
        mine_update_tv.text = getString(msg)
    }

    override fun createFPresenter(): FMinePresenter {
        return MinePresenterImpl(this)
    }

    override fun getViewId(): Int {
        return R.layout.fragment_mine
    }

    @OnClick(R.id.mine_collect_ll, R.id.mine_night_ll, R.id.mine_setting_ll
            , R.id.mine_notice_rl, R.id.mine_comment_rl, R.id.mine_feedback_rl
            , R.id.mine_update_rl)
    override fun onClick(v: View?) {
        super.onClick(v)

        when (v!!.id) {
            R.id.mine_collect_ll ->
                mvpPresenter!!.startCollect()
            R.id.mine_night_ll ->
                changeTheme()
//                mine_constraint_layout.setBackgroundColor(resources.getColor(R.color.backgroundColor_night))
            R.id.mine_setting_ll ->
                Logger.e("setting")
            R.id.mine_notice_rl ->
                Logger.e("notice")
            R.id.mine_comment_rl ->
                Logger.e("comment")
            R.id.mine_feedback_rl ->
                Logger.e("feedback")
            R.id.mine_update_rl ->
                Logger.e("update")
        }
    }

    private fun changeTheme() {
        var uiMode = resources.configuration.uiMode
        var nightMask = Configuration.UI_MODE_NIGHT_MASK
        var activity = activityContext as BaseActivity<*>
        activity.changeMode(uiMode and nightMask)
    }

    override fun initData(savedInstanceState: Bundle?) {

        //获取当前的版本号
        mvpPresenter!!.checkCurVersion()

        ThemeManager.registerThemeChangeListener(this)
    }
    override fun startLogin() {
        var intent = Intent(activityContext, LoginActivity::class.java)
        activityContext.startActivity(intent)
    }

    companion object {
        fun newInstance(tag: String): Fragment {
            var fragment = MineFragment()
            var bundle = Bundle()
            bundle.putString(Config.FRAGMENT_TAG, tag)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onDestroy() {
        ThemeManager.unRegisterThemeChangeListener(this)
        super.onDestroy()
    }
}
