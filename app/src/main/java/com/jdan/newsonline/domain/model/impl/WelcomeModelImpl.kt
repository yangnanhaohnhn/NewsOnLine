package com.jdan.newsonline.domain.model.impl

import com.jdan.newsonline.domain.bean.StatusExplain
import com.jdan.newsonline.domain.bean.VersionBean
import com.jdan.newsonline.domain.model.IWelcomeModel
import com.jdan.newsonline.mvp.BaseModelImpl
import com.jdan.newsonline.util.MathUtils
import com.jdan.newsonline.widget.callback.ResCallBack
import com.jdan.newsonline.widget.callback.ReqResultCallBack
import com.orhanobut.logger.Logger

class WelcomeModelImpl : BaseModelImpl(),IWelcomeModel {
    override fun checkCurVersion(callBack: ResCallBack<VersionBean>) {
        addSubscription(apiStores.checkVersionReq(MathUtils.getRandomStr()),object : ReqResultCallBack<VersionBean>{
            override fun onFailure(code: Int, msg: String?) {
                callBack.onFailure(msg)
            }

            override fun onCompleted() {
                callBack.onCompleted()
            }

            override fun onSuccess(model: VersionBean) {
                Logger.e(model.toString())
                if (model.status == 0){
                    callBack.onSuccess(model)
                }else{
                    callBack.onFailure(StatusExplain.getItemByCode(model.status))
                }
            }
        })
    }
}
