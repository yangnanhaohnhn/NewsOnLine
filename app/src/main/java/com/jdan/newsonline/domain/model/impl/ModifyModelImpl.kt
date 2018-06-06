package com.jdan.newsonline.domain.model.impl

import com.jdan.newsonline.domain.bean.BaseBean
import com.jdan.newsonline.domain.model.IModifyModel
import com.jdan.newsonline.mvp.BaseModelImpl
import com.jdan.newsonline.widget.callback.ResCallBack

class ModifyModelImpl : BaseModelImpl(),IModifyModel {
    override fun modifyPwd(user_id: String?, oldPwd: String?, newPwd: String?, resCallBack: ResCallBack<BaseBean>) {
//        addSubscription(apiStores.modifyPwdReq())
    }

}
