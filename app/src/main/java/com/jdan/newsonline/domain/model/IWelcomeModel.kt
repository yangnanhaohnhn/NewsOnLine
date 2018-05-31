package com.jdan.newsonline.domain.model

import com.jdan.newsonline.domain.bean.VersionBean
import com.jdan.newsonline.mvp.BaseModel
import com.jdan.newsonline.widget.callback.ResCallBack

interface IWelcomeModel : BaseModel {
    fun checkCurVersion(res: ResCallBack<VersionBean>)

}
