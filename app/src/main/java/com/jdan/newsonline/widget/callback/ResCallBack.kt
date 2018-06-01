package com.jdan.newsonline.widget.callback


open class ResCallBack<M> : ReqResultCallBack<M>{
    /**
     * 成功
     */
    override fun onSuccess(model: M) {
    }

    /**
     * 完成
     */
    override fun onCompleted() {
    }

    override fun onFailure(code: Int, msg: String?) {

    }

//    /**
//     * 成功
//     */
//    fun onSuccess(model: M)
//
//    fun onFailure(msg:String?)
//
//    /**
//     * 完成
//     */
//    fun onCompleted()
}