package com.jdan.newsonline.widget.callback


interface ResCallBack<M>{
    /**
     * 成功
     */
    fun onSuccess(model: M)

    fun onFailure(msg:String?)

    /**
     * 完成
     */
    fun onCompleted()
}