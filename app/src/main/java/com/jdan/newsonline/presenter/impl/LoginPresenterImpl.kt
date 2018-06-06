package com.jdan.newsonline.presenter.impl

import android.support.design.widget.Snackbar
import cn.sharesdk.framework.Platform
import cn.sharesdk.framework.PlatformActionListener
import cn.sharesdk.framework.ShareSDK
import cn.sharesdk.sina.weibo.SinaWeibo
import cn.sharesdk.tencent.qq.QQ
import cn.sharesdk.wechat.friends.Wechat
import com.jdan.newsonline.R
import com.jdan.newsonline.domain.bean.inner.UserBean
import com.jdan.newsonline.domain.constants.Config
import com.jdan.newsonline.domain.model.ILoginModel
import com.jdan.newsonline.domain.model.impl.LoginModelImpl
import com.jdan.newsonline.mvp.BaseModel
import com.jdan.newsonline.mvp.BaseModelImpl
import com.jdan.newsonline.mvp.BasePresenterImpl
import com.jdan.newsonline.presenter.ILoginPresenter
import com.jdan.newsonline.ui.view.ILoginView
import com.jdan.newsonline.util.*
import com.jdan.newsonline.widget.callback.ResCallBack
import com.orhanobut.logger.Logger
import java.util.*
import kotlin.collections.HashMap

class LoginPresenterImpl(view: ILoginView) : BasePresenterImpl<ILoginView, ILoginModel>(),ILoginPresenter, PlatformActionListener {
    override fun login() {
        //登录
        var phoneEt = mvpView!!.phoneEt
        if (StringUtils.isEmpty(phoneEt)){
            Snackbar.make(mvpView!!.loginConstraintLayout, R.string.input_phone_hint,Snackbar.LENGTH_LONG).show()
            return
        }
        if (!StringUtils.isPhoneNumber(phoneEt)){
            Snackbar.make(mvpView!!.loginConstraintLayout, R.string.input_phone_format_error,Snackbar.LENGTH_LONG).show()
            return
        }
        var pwdEt = mvpView!!.pwdEt
        if (StringUtils.isEmpty(mvpView!!.pwdEt)){
            Snackbar.make(mvpView!!.loginConstraintLayout, R.string.input_pwd_hint,Snackbar.LENGTH_LONG).show()
            return
        }
        mvpView!!.showLoading()
        var params = HashMap<String,String>()
        params.put(Config.USERNAME,phoneEt)
        params.put(Config.PASSWORD,Md5Utils.toMD5(pwdEt))
        params.put(Config.LOGIN_STYLE,MathUtils.getUniqueId(mvpView!!.activityContext))
        mvpModel!!.login(params,object : ResCallBack<UserBean>(){
            /**
             * 成功
             */
            override fun onSuccess(model: UserBean) {
                mvpView!!.toastShow(R.string.login_success)
                //保存用户信息
                sharedUtil!!.putBoolean(Config.IS_LOGIN,true)
                sharedUtil!!.putString(Config.PASSWORD,Md5Utils.toMD5(pwdEt))
                sharedUtil!!.putBean(Config.USER_INFO,JsonUtils.createJsonStr(model))
                mvpView!!.startMainActivity()
            }

            /**
             * 完成
             */
            override fun onCompleted() {
                mvpView!!.hideLoading()
            }

            override fun onFailure(code: Int, msg: String?) {
                ShowDialogUtils.showIOSSingleNoCallback(mvpView!!.activityContext,msg!!)
            }
        })
    }

    override fun onComplete(p0: Platform?, p1: Int, p2: HashMap<String, Any>?) {
        Logger.e(p2.toString())
    }

    override fun onCancel(p0: Platform?, p1: Int) {
    }

    override fun onError(p0: Platform?, p1: Int, p2: Throwable?) {
        Logger.e(""+p1)
    }

    override fun otherLogin(name: String) {
        var plat : Platform? = null
        if (StringUtils.isEquals(name,Config.WX)){
            plat = ShareSDK.getPlatform(Wechat.NAME)
        }else if(StringUtils.isEquals(name,Config.QQ)){
            plat = ShareSDK.getPlatform(QQ.NAME)
        }else{
            plat = ShareSDK.getPlatform(SinaWeibo.NAME)
        }

        //移除授权状态和本地缓存,下次授权会重新授权
        plat.removeAccount(true)
        plat.SSOSetting(false) //SSO授权，传false默认是客户端授权，没有客户端授权或者不支持客户端授权会跳web授权
        plat.platformActionListener = this//授权回调监听，监听oncomplete，onerror，oncancel三种状态
        if(plat.isClientValid){
            //判断是否存在授权凭条的客户端，true是有客户端，false是无
        }
        if(plat.isAuthValid){
            //判断是否已经存在授权状态，可以根据自己的登录逻辑设置
            mvpView!!.toastShow("已经授权过了")
            return
        }
        //plat.authorize();	//要功能，不要数据
        plat.showUser(null)   //要数据不要功能，主要体现在不会重复出现授权界面
    }

    init {
        attachView(view, LoginModelImpl())
    }
}
