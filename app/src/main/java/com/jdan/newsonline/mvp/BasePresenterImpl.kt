package com.jdan.newsonline.mvp

import com.jdan.newsonline.util.SharedUtil

open class BasePresenterImpl<V : BaseView, M : BaseModel> : BasePresenter {
    protected var mvpView: V? = null
    protected var mvpModel: M? = null

    protected var sharedUtil:SharedUtil? =null

    override fun detachView() {
        this.mvpView = null
        mvpModel!!.detachObservable()
    }


    /**
     * 初始化
     */
    override fun attachView(view: BaseView, model: BaseModel) {
        mvpView = view as V
        mvpModel = model as M

        sharedUtil = SharedUtil.getInstance(mvpView!!.activityContext)
    }

}