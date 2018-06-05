package com.jdan.newsonline.ui.view

import android.support.constraint.ConstraintLayout
import android.widget.Button
import android.widget.EditText
import com.jdan.newsonline.mvp.BaseView

interface IRegisterView:BaseView {
    val mRegisterPhone: String
    val registerConstraintLayout: ConstraintLayout
    val phoneCodeBtn: Button
    val phoneCodeStr: String

}
