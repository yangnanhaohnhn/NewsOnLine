package com.jdan.newsonline.domain.model.impl

import com.jdan.newsonline.domain.bean.BaseBean
import com.jdan.newsonline.domain.bean.RegisterBean
import com.jdan.newsonline.domain.model.IRegisterModel
import com.jdan.newsonline.mvp.BaseModelImpl
import com.jdan.newsonline.util.MathUtils
import com.jdan.newsonline.widget.callback.ReqResultCallBack
import com.jdan.newsonline.widget.callback.ResCallBack

/**
 * 注册
 */
class RegisterModelImpl : BaseModelImpl(), IRegisterModel {
    override fun registerUser(phoneStr: String, loginStyle: String, resCallBack: ResCallBack<RegisterBean>) {
        addSubscription(apiStores.registerUserReq(phoneStr,loginStyle,MathUtils.getRandomStr()),object : ReqResultCallBack<RegisterBean> {
            /**
             * 成功
             */
            override fun onSuccess(model: RegisterBean) {
            }

            override fun onFailure(code: Int, msg: String?) {
            }

            /**
             * 完成
             */
            override fun onCompleted() {
            }

        })
    }
}
