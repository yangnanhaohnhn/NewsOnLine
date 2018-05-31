package com.jdan.newsonline.domain.model.impl

import com.jdan.newsonline.domain.bean.StatusExplain
import com.jdan.newsonline.domain.bean.VersionBean
import com.jdan.newsonline.domain.model.FMineModel
import com.jdan.newsonline.mvp.BaseModelImpl
import com.jdan.newsonline.util.MathUtils
import com.jdan.newsonline.widget.callback.ReqResultCallBack
import com.jdan.newsonline.widget.callback.ResCallBack

class MineModelImpl : BaseModelImpl(),FMineModel {
    override fun checkCurVersion(resCallBack: ResCallBack<VersionBean>) {
        addSubscription(apiStores.checkVersionReq(MathUtils.getRandomStr()),object : ReqResultCallBack<VersionBean> {
            override fun onFailure(code: Int, msg: String?) {
                resCallBack.onFailure(msg)
            }

            override fun onCompleted() {
                resCallBack.onCompleted()
            }

            override fun onSuccess(model: VersionBean) {
                if (model.status == 0){
                    resCallBack.onSuccess(model)
                }else{
                    resCallBack.onFailure(StatusExplain.getItemByCode(model.status))
                }
            }
        })
    }

}
