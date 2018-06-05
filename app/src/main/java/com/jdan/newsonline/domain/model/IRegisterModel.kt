package com.jdan.newsonline.domain.model

import com.jdan.newsonline.domain.bean.BaseBean
import com.jdan.newsonline.domain.bean.RegisterBean
import com.jdan.newsonline.mvp.BaseModel
import com.jdan.newsonline.widget.callback.ResCallBack

interface IRegisterModel : BaseModel{
    fun registerUser(phoneStr: String, loginStyle: String, resCallBack: ResCallBack<RegisterBean>)

}
