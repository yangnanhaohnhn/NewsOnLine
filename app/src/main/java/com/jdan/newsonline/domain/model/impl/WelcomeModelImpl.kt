package com.jdan.newsonline.domain.model.impl

import com.jdan.newsonline.domain.bean.VersionBean
import com.jdan.newsonline.domain.model.IWelcomeModel
import com.jdan.newsonline.mvp.BaseModelImpl
import com.jdan.newsonline.widget.callback.ResCallBack

class WelcomeModelImpl : BaseModelImpl(),IWelcomeModel {
    override fun checkCurVersion(callBack: ResCallBack<VersionBean>) {
        checkCurVer(callBack)
    }
}
