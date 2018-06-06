package com.jdan.newsonline.mvp

import com.jdan.newsonline.domain.bean.BaseBean
import com.jdan.newsonline.domain.bean.UserBean
import com.jdan.newsonline.widget.callback.ResCallBack

interface BaseModel {
    /**
     * 取消观察着
     */
    fun detachObservable()

}