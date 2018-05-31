package com.jdan.newsonline.presenter.impl

import com.jdan.newsonline.R
import com.jdan.newsonline.domain.bean.VersionBean
import com.jdan.newsonline.domain.model.FMineModel
import com.jdan.newsonline.domain.model.impl.MineModelImpl
import com.jdan.newsonline.mvp.BasePresenterImpl
import com.jdan.newsonline.presenter.FMinePresenter
import com.jdan.newsonline.ui.view.FMineView
import com.jdan.newsonline.util.AppUtils
import com.jdan.newsonline.widget.callback.ResCallBack

class MinePresenterImpl(view: FMineView) : BasePresenterImpl<FMineView, FMineModel>() ,FMinePresenter {
    override fun checkCurVersion() {
        //获取当前版本号
        mvpModel!!.checkCurVersion(object : ResCallBack<VersionBean> {
            override fun onSuccess(model: VersionBean) {
                var code = AppUtils.getAppVersionCode() //当前的version
                if (code < model.version!!.toInt()){
                    //更新
                    mvpView!!.showUpdateTv(R.string.find_new_version)
                }else{
                    //不更新
                    mvpView!!.showUpdateTv(R.string.already_new_version)
                }
            }

            override fun onFailure(msg: String?) {
                mvpView!!.showUpdateTv(R.string.already_new_version)
            }

            override fun onCompleted() {
            }
        })
    }

    init {
        attachView(view, MineModelImpl())
    }

}
