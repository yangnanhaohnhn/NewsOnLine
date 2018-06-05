package com.jdan.newsonline.listener

interface SmsFinishListener {
    fun onSuccess(tag: String)
    fun onFailure()
}