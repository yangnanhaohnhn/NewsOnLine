package com.jdan.newsonline.widget.callback


interface ReqResultCallBack<M>{
    /**
     * 成功
     */
    fun onSuccess(model: M)

    fun onFailure(code: Int,msg:String?)

    /**
     * 完成
     */
    fun onCompleted()
}