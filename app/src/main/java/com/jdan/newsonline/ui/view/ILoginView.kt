package com.jdan.newsonline.ui.view

import android.support.constraint.ConstraintLayout
import com.jdan.newsonline.mvp.BaseView

interface ILoginView : BaseView{
    val phoneEt: String
    val pwdEt : String
    val loginConstraintLayout: ConstraintLayout

}
