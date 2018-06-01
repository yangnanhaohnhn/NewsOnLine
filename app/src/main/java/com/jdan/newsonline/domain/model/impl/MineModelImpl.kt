package com.jdan.newsonline.domain.model.impl

import com.jdan.newsonline.domain.bean.VersionBean
import com.jdan.newsonline.domain.model.FMineModel
import com.jdan.newsonline.mvp.BaseModelImpl
import com.jdan.newsonline.widget.callback.ResCallBack

class MineModelImpl : BaseModelImpl(), FMineModel {
    override fun checkCurVersion(resCallBack: ResCallBack<VersionBean>) {
        checkCurVer(resCallBack)
    }
}
