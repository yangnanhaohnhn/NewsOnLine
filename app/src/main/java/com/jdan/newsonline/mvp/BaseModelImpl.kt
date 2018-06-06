package com.jdan.newsonline.mvp

import com.jdan.newsonline.domain.bean.StatusExplain
import com.jdan.newsonline.domain.bean.VersionBean
import com.jdan.newsonline.util.MathUtils
import com.jdan.newsonline.widget.callback.ReqResultCallBack
import com.jdan.newsonline.widget.callback.ResCallBack
import com.jdan.newsonline.widget.callback.SubscriberCallBack
import com.jdan.newsonline.widget.retrofit.ApiStores
import com.jdan.newsonline.widget.retrofit.AppClient
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import rx.subscriptions.CompositeSubscription

open class BaseModelImpl : BaseModel {
    private var mCompositeSubscription : CompositeSubscription? = null
    var apiStores = AppClient.retrofit()!!.create(ApiStores:: class.java) as ApiStores

    override fun detachObservable() {
        unSubscribe()
    }
    /**
     * 添加请求的观察着
     */
    fun <M : Any?> addSubscription(observable: Observable<M>, callBack: ReqResultCallBack<M>){
        if (mCompositeSubscription == null){
            mCompositeSubscription = CompositeSubscription();
        }
        mCompositeSubscription!!.add(observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(SubscriberCallBack(callBack)))

    }

    /**
     * RxJava取消注册,以避免内存泄漏
     */
    private fun unSubscribe() {
        if (mCompositeSubscription != null && mCompositeSubscription!!.hasSubscriptions()){
            mCompositeSubscription!!.unsubscribe();
        }
    }

    /**
     * 检查当前版本
     */
    fun checkCurVer(resCallBack: ResCallBack<VersionBean>){
        addSubscription(apiStores.checkVersionReq(MathUtils.getRandomStr()),object : ReqResultCallBack<VersionBean>{
            /**
             * 成功
             */
            override fun onSuccess(model: VersionBean) {
                if (model.status == 0){
                    resCallBack.onSuccess(model)
                }else{
                    resCallBack.onFailure(model.status,model.msg)
                }
            }

            override fun onFailure(code: Int, msg: String?) {
                resCallBack.onFailure(code,msg)
            }

            /**
             * 完成
             */
            override fun onCompleted() {
                resCallBack.onCompleted()
            }

        })
    }


}