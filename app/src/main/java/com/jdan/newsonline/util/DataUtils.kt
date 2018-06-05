package com.jdan.newsonline.util

import cn.smssdk.EventHandler
import cn.smssdk.SMSSDK
import com.jdan.newsonline.domain.constants.Config
import com.jdan.newsonline.listener.SmsFinishListener


object DataUtils {
    // 请求验证码，其中country表示国家代码，如“86”；phone表示手机号码，如“13800138000”
    fun sendCode(country:String,phone:String,callback:SmsFinishListener){
        SMSSDK.registerEventHandler(object : EventHandler() {

            override fun afterEvent(event: Int, result: Int, data: Any?) {
                if (result == SMSSDK.RESULT_COMPLETE) {
                    // TODO 处理成功得到验证码的结果
                    // 请注意，此时只是完成了发送验证码的请求，验证码短信还需要几秒钟之后才送达
                    if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                        //提交验证码成功
                        callback.onSuccess(Config.COMMIT_SUCCESS)
                    }else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE){
                        //获取验证码成功
                        callback.onSuccess(Config.GET_SUCCESS)
                    }else if (event ==SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES){
                        //返回支持发送验证码的国家列表
                    }
                } else {
                    // TODO 处理错误的结果
                    callback.onFailure()
                }
            }
        })
        // 触发操作
        SMSSDK.getVerificationCode(country, phone)
    }

    // 提交验证码，其中的code表示验证码，如“1357”
    fun submitCode(country: String, phone: String, code: String,callback: SmsFinishListener) {
        // 注册一个事件回调，用于处理提交验证码操作的结果
        SMSSDK.registerEventHandler(object : EventHandler() {
            override fun afterEvent(event: Int, result: Int, data: Any?) {
                if (result == SMSSDK.RESULT_COMPLETE) {
                    // TODO 处理验证成功的结果
                    callback.onSuccess(Config.GET_SUCCESS)
                } else {
                    // TODO 处理错误的结果
                    callback.onFailure()
                }

            }
        })
        // 触发操作
        SMSSDK.submitVerificationCode(country, phone, code)
    }
}