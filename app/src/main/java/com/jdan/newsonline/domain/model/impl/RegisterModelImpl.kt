package com.jdan.newsonline.domain.model.impl

import com.jdan.newsonline.domain.bean.BaseBean
import com.jdan.newsonline.domain.bean.StatusExplain
import com.jdan.newsonline.domain.model.IRegisterModel
import com.jdan.newsonline.mvp.BaseModelImpl
import com.jdan.newsonline.util.MathUtils
import com.jdan.newsonline.widget.callback.ReqResultCallBack
import com.jdan.newsonline.widget.callback.ResCallBack

/**
 * 注册
 */
class RegisterModelImpl : BaseModelImpl(), IRegisterModel {
    override fun registerUser(phoneStr: String, loginStyle: String, resCallBack: ResCallBack<BaseBean>) {
        addSubscription(apiStores.registerUserReq(phoneStr,loginStyle,MathUtils.getRandomStr()),object : ReqResultCallBack<BaseBean> {
            /**
             * 成功
             */
            override fun onSuccess(model: BaseBean) {
                if (model.status == 0){
                    resCallBack.onSuccess(model)
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
