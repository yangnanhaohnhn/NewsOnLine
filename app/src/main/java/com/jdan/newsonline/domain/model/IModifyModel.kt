package com.jdan.newsonline.domain.model

import com.jdan.newsonline.domain.bean.BaseBean
import com.jdan.newsonline.mvp.BaseModel
import com.jdan.newsonline.widget.callback.ResCallBack

interface IModifyModel:BaseModel {
    fun modifyPwd(user_id: String?, oldPwd: String?, newPwd: String?, resCallBack: ResCallBack<BaseBean>)

}
