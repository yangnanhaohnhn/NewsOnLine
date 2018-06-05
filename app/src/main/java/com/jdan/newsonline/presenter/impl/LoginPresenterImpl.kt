package com.jdan.newsonline.presenter.impl

import cn.sharesdk.framework.Platform
import cn.sharesdk.framework.PlatformActionListener
import cn.sharesdk.framework.ShareSDK
import cn.sharesdk.sina.weibo.SinaWeibo
import cn.sharesdk.tencent.qq.QQ
import cn.sharesdk.wechat.friends.Wechat
import com.jdan.newsonline.domain.constants.Config
import com.jdan.newsonline.mvp.BaseModel
import com.jdan.newsonline.mvp.BaseModelImpl
import com.jdan.newsonline.mvp.BasePresenterImpl
import com.jdan.newsonline.presenter.ILoginPresenter
import com.jdan.newsonline.ui.view.ILoginView
import com.jdan.newsonline.util.StringUtils
import com.orhanobut.logger.Logger
import java.util.*

class LoginPresenterImpl(view: ILoginView) : BasePresenterImpl<ILoginView,BaseModel>(),ILoginPresenter, PlatformActionListener {
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
        attachView(view,BaseModelImpl())
    }
}
