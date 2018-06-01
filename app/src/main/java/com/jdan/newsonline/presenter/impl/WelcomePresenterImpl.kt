package com.jdan.newsonline.presenter.impl

import android.os.Build
import com.jdan.newsonline.R
import com.jdan.newsonline.domain.bean.VersionBean
import com.jdan.newsonline.domain.constants.Config
import com.jdan.newsonline.domain.model.IWelcomeModel
import com.jdan.newsonline.domain.model.impl.WelcomeModelImpl
import com.jdan.newsonline.mvp.BasePresenterImpl
import com.jdan.newsonline.presenter.IWelcomePresenter
import com.jdan.newsonline.ui.view.IWelcomeView
import com.jdan.newsonline.util.AppUtils
import com.jdan.newsonline.widget.callback.ResCallBack
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener

class WelcomePresenterImpl(view: IWelcomeView) : BasePresenterImpl<IWelcomeView, IWelcomeModel>(),IWelcomePresenter {
    override fun initPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            //之前的请求没做处理
            Dexter.checkPermissions(MultiplePermissionListener(), "android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_EXTERNAL_STORAGE")
        }else{
            checkCurVersion()
        }
    }

    override fun checkCurVersion() {
        //检查当前的版本号
        mvpView!!.showLoading()
        mvpModel!!.checkCurVersion(object : ResCallBack<VersionBean>(){
            override fun onSuccess(model: VersionBean) {
                var code = AppUtils.getAppVersionCode() //当前的version
                if (code < model.version!!.toInt()){
                    //TODO 更新
                }else{
                    //不更新
                    if (isFirstLogin()){
                        //是第一次登陆
                        mvpView!!.startGuideActivity()
                    }else{
                        mvpView!!.startMainActivity()
                    }
                }
            }

            override fun onFailure(code: Int,msg: String?) {
                mvpView!!.startMainActivity()
            }

            override fun onCompleted() {
                mvpView!!.hideLoading()
            }
        })
    }

    init {
        //初始化
        attachView(view, WelcomeModelImpl())
    }

    fun isFirstLogin():Boolean{
       return sharedUtil!!.getBoolean(Config.IS_FIRST_LOGIN,true)
    }

    inner class MultiplePermissionListener: MultiplePermissionsListener {
         override fun onPermissionRationaleShouldBeShown(permissions: MutableList<PermissionRequest>?, token: PermissionToken?) {
             token!!.continuePermissionRequest()
         }

         override fun onPermissionsChecked(report: MultiplePermissionsReport?) {
             for (response in report!!.deniedPermissionResponses) {
                 mvpView!!.toastShow(R.string.need_agree_all_permission_req)
                 return
             }
             checkCurVersion()
        }

    }
}
