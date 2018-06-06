package com.jdan.newsonline.ui.view

import com.jdan.newsonline.mvp.BaseView

interface IModifyView : BaseView{
    val oldPwdStr : String
    val newPwdStr : String
    val againNewPwdStr : String

}
