package com.jdan.newsonline.presenter.impl

import com.jdan.newsonline.R
import com.jdan.newsonline.domain.bean.BaseBean
import com.jdan.newsonline.domain.bean.inner.UserBean
import com.jdan.newsonline.domain.constants.Config
import com.jdan.newsonline.domain.model.IModifyModel
import com.jdan.newsonline.domain.model.impl.ModifyModelImpl
import com.jdan.newsonline.mvp.BaseModel
import com.jdan.newsonline.mvp.BaseModelImpl
import com.jdan.newsonline.mvp.BasePresenterImpl
import com.jdan.newsonline.presenter.IModifyPresenter
import com.jdan.newsonline.ui.view.IModifyView
import com.jdan.newsonline.util.Md5Utils
import com.jdan.newsonline.util.ShowDialogUtils
import com.jdan.newsonline.util.StringUtils
import com.jdan.newsonline.widget.callback.ResCallBack

class ModifyPwdPresenterImpl(view: IModifyView) : BasePresenterImpl<IModifyView, IModifyModel>(), IModifyPresenter {
    override fun modifyPwd() {
        var oldPwdStr = mvpView!!.oldPwdStr
        var newPwdStr = mvpView!!.newPwdStr
        var againNewPwdStr = mvpView!!.againNewPwdStr
        var password = Md5Utils.MD5(sharedUtil!!.getString(Config.PASSWORD, ""))
       var userBean =  sharedUtil!!.getBean(Config.USER_INFO,UserBean::class.java) as UserBean
        if (StringUtils.isEmpty(oldPwdStr)) {
            ShowDialogUtils.showIOSSingleNoCallback(mvpView!!.activityContext, R.string.input_old_pwd_hint)
            return
        }
        if (StringUtils.isEmpty(newPwdStr)) {
            ShowDialogUtils.showIOSSingleNoCallback(mvpView!!.activityContext, R.string.input_new_pwd_hint)
            return
        }
        if (StringUtils.isEmpty(againNewPwdStr)) {
            ShowDialogUtils.showIOSSingleNoCallback(mvpView!!.activityContext, R.string.again_new_pwd_hint)
            return
        }
        if (!StringUtils.isEquals(password, Config.DEFAULT_PWD) && StringUtils.isEquals(oldPwdStr, Config.DEFAULT_PWD)) {
            ShowDialogUtils.showIOSSingleNoCallback(mvpView!!.activityContext, R.string.not_use_init_pwd)
            return
        }
        if (!StringUtils.isEquals(oldPwdStr, password)) {
            ShowDialogUtils.showIOSSingleNoCallback(mvpView!!.activityContext, R.string.input_old_pwd_hint)
            return
        }
        if (!StringUtils.checkPwd(newPwdStr)){
            ShowDialogUtils.showIOSSingleNoCallback(mvpView!!.activityContext, R.string.pwd_format_error)
            return
        }
        if (!StringUtils.isEquals(newPwdStr,Config.DEFAULT_PWD)){
            ShowDialogUtils.showIOSSingleNoCallback(mvpView!!.activityContext, R.string.not_use_init_pwd)
            return
        }
        if (StringUtils.isEquals(newPwdStr,password)){
            ShowDialogUtils.showIOSSingleNoCallback(mvpView!!.activityContext, R.string.not_equal_alreay_pwd)
            return
        }
        if (!StringUtils.isEquals(newPwdStr,againNewPwdStr)){
            ShowDialogUtils.showIOSSingleNoCallback(mvpView!!.activityContext, R.string.again_new_pwd_hint)
            return
        }
        mvpView!!.showLoading()
        mvpModel!!.modifyPwd(userBean.user_id,Md5Utils.toMD5(oldPwdStr),Md5Utils.toMD5(newPwdStr),object: ResCallBack<BaseBean>(){

        })
    }

    init {
        attachView(view, ModifyModelImpl())
    }
}
