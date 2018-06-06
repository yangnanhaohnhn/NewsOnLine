package com.jdan.newsonline.domain.model.impl

import com.jdan.newsonline.domain.bean.inner.UserBean
import com.jdan.newsonline.domain.bean.outer.UserBeanOut
import com.jdan.newsonline.domain.model.ILoginModel
import com.jdan.newsonline.mvp.BaseModelImpl
import com.jdan.newsonline.util.MathUtils
import com.jdan.newsonline.widget.callback.ReqResultCallBack
import com.jdan.newsonline.widget.callback.ResCallBack

class LoginModelImpl : BaseModelImpl(), ILoginModel {
    override fun login(param: HashMap<String, String>, resCallBack: ResCallBack<UserBean>) {
        addSubscription(apiStores.loginReq(param,MathUtils.getRandomStr()),object : ReqResultCallBack<UserBeanOut>{
            /**
             * 成功
             */
            override fun onSuccess(model: UserBeanOut) {
                if (model.status == 0){
                    resCallBack.onSuccess(model.userBean!!)
                }else{
                    resCallBack.onFailure(model.status,model.msg)
                }
            }

            override fun onFailure(code: Int, msg: String?) {
                resCallBack.onFailure(code,msg)
            }

            /**
             * 完成
             */
            override fun onCompleted() {
                resCallBack.onCompleted()
            }
        })
    }

}
