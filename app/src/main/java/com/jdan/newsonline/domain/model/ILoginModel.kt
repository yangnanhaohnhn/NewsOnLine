package com.jdan.newsonline.domain.model

import com.jdan.newsonline.domain.bean.inner.UserBean
import com.jdan.newsonline.mvp.BaseModel
import com.jdan.newsonline.widget.callback.ResCallBack

interface ILoginModel : BaseModel{
    fun login(param:HashMap<String,String>, resCallBack: ResCallBack<UserBean>)
}
