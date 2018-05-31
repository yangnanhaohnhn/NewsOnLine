package com.jdan.newsonline.widget.callback

import retrofit2.adapter.rxjava.HttpException
import rx.Subscriber


class SubscriberCallBack<M>(var callBack: ReqResultCallBack<M>) : Subscriber<M>() {

    override fun onNext(m: M) {
        callBack.onSuccess(m)
    }

    override fun onError(e: Throwable?) {
        e!!.printStackTrace()
        if (e is HttpException){
            val code = e.code();
            var msg = e.message;
            if (code == 504){
                msg="网络不给力"
            }
            callBack.onFailure(code,msg)
        }else{
            callBack.onFailure(0,e.message)
        }
        callBack.onCompleted()
    }

    override fun onCompleted() {
        callBack.onCompleted()
    }

}