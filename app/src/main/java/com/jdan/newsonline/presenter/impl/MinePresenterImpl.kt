package com.jdan.newsonline.presenter.impl

import com.jdan.newsonline.R
import com.jdan.newsonline.domain.bean.VersionBean
import com.jdan.newsonline.domain.constants.Config
import com.jdan.newsonline.domain.model.FMineModel
import com.jdan.newsonline.domain.model.impl.MineModelImpl
import com.jdan.newsonline.mvp.BasePresenterImpl
import com.jdan.newsonline.presenter.FMinePresenter
import com.jdan.newsonline.ui.view.FMineView
import com.jdan.newsonline.util.AppUtils
import com.jdan.newsonline.util.ThemeManager
import com.jdan.newsonline.widget.callback.ResCallBack
import com.orhanobut.logger.Logger

class MinePresenterImpl(view: FMineView) : BasePresenterImpl<FMineView, FMineModel>() ,FMinePresenter {

    override fun changeMode() {
        if (ThemeManager.getThemeMode() == ThemeManager.ThemeMode.DAY){
            ThemeManager.setThemeMode(ThemeManager.ThemeMode.NIGHT)
        }else{
            ThemeManager.setThemeMode(ThemeManager.ThemeMode.DAY)
        }
    }

    /**
     * 跳转到收藏
     */
    override fun startCollect() {
        //先判断是否登录了
         if(isLogin()){
            //登录了
         }else{
             //没有登录
             mvpView!!.startLogin()
         }
    }

    private fun isLogin(): Boolean {
        return sharedUtil!!.getBoolean(Config.IS_LOGIN,false)
    }

    override fun checkCurVersion() {
        //获取当前版本号
        mvpModel!!.checkCurVersion(object : ResCallBack<VersionBean>() {
            override fun onSuccess(model: VersionBean) {
                Logger.e("model :"+model.toString())
                var code = AppUtils.getAppVersionCode() //当前的version
                if (code < model.version!!.toInt()){
                    //更新
                    mvpView!!.showUpdateTv(R.string.find_new_version)
                }else{
                    //不更新
                    mvpView!!.showUpdateTv(R.string.already_new_version)
                }
            }

            override fun onFailure(code: Int,msg: String?) {
                mvpView!!.showUpdateTv(R.string.already_new_version)
            }
        })
    }

    init {
        attachView(view, MineModelImpl())
    }

}
