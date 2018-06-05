package com.jdan.newsonline.presenter.impl

import android.os.Handler
import android.os.Message
import android.support.design.widget.Snackbar
import com.jdan.newsonline.R
import com.jdan.newsonline.domain.bean.BaseBean
import com.jdan.newsonline.domain.bean.RegisterBean
import com.jdan.newsonline.domain.constants.Config
import com.jdan.newsonline.domain.model.IRegisterModel
import com.jdan.newsonline.domain.model.impl.RegisterModelImpl
import com.jdan.newsonline.listener.SmsFinishListener
import com.jdan.newsonline.mvp.BasePresenterImpl
import com.jdan.newsonline.presenter.IRegisterPresenter
import com.jdan.newsonline.ui.view.IRegisterView
import com.jdan.newsonline.util.DataUtils
import com.jdan.newsonline.util.MathUtils
import com.jdan.newsonline.util.StringUtils
import com.jdan.newsonline.widget.callback.ResCallBack

class RegisterPresenterImpl(view: IRegisterView) :BasePresenterImpl<IRegisterView, IRegisterModel>(), IRegisterPresenter {

    private var mHandler: Handler? = object : Handler() {
        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            when(msg!!.what){
                Config.SEND_PHONE_CODE ->
                        sendPhone()
                Config.CHANGE_CODE_TIME ->
                        changeTime()

            }
        }

        private fun changeTime() {
            var mPhoneCodeStr = mvpView!!.phoneCodeBtn.text.toString().trim()
            var timeNum = mPhoneCodeStr.substring(0,mPhoneCodeStr.length - 1).toInt()
            if (timeNum == 0){
                mvpView!!.phoneCodeBtn.text = mvpView!!.activityContext.getString(R.string.get_phone_code)
                mvpView!!.phoneCodeBtn.isClickable = true
                removeMessages(Config.CHANGE_CODE_TIME)
                return
            }
            timeNum -= 1
            mvpView!!.phoneCodeBtn.text = timeNum.toString() + "s"
            sendEmptyMessageDelayed(Config.CHANGE_CODE_TIME,1000)
        }

        private fun sendPhone() {
            mvpView!!.phoneCodeBtn.text = "50s"
            mvpView!!.phoneCodeBtn.isClickable = false

            removeMessages(Config.SEND_PHONE_CODE)
            sendEmptyMessageDelayed(Config.CHANGE_CODE_TIME,1000)
        }
    }

    override fun enterNews() {
        //进入新闻
        var phoneStr = mvpView!!.mRegisterPhone
        var phoneCodeStr = mvpView!!.phoneCodeStr
        if (StringUtils.isEmpty(phoneStr)){
            mvpView!!.toastShow(R.string.input_phone_hint)
            return
        }
        if (StringUtils.isEmpty(phoneCodeStr)){
            mvpView!!.toastShow(R.string.input_phone_code_hint)
            return
        }
        mvpView!!.showLoading()
        DataUtils.submitCode(Config.COUNTRY,phoneStr,phoneCodeStr,object : SmsFinishListener{
            override fun onSuccess(tag: String) {
                //获取成功
                mvpModel!!.registerUser(phoneStr,MathUtils.getUniqueId(mvpView!!.activityContext),object : ResCallBack<RegisterBean>(){

                })
            }

            override fun onFailure() {
                //验证码错误
                mvpView!!.hideLoading()
                Snackbar.make(mvpView!!.registerConstraintLayout,R.string.phone_code_error,Snackbar.LENGTH_LONG).show()
            }
        })
    }

    override fun getPhoneCode() {
        //获取验证码
       var mRegisterPhone =  mvpView!!.mRegisterPhone
        if (StringUtils.isEmpty(mRegisterPhone)){
            mvpView!!.toastShow(R.string.input_phone_hint)
            return
        }
        if (!StringUtils.isPhoneNumber(mRegisterPhone)){
            mvpView!!.toastShow(R.string.input_phone_format_error)
            return
        }
        //获取验证码
        DataUtils.sendCode(Config.COUNTRY,mRegisterPhone,object : SmsFinishListener{
            override fun onSuccess(tag: String) {
                if (StringUtils.isEquals(tag,Config.COMMIT_SUCCESS)) {
                    Snackbar.make(mvpView!!.registerConstraintLayout, tag, Snackbar.LENGTH_LONG).show()
                }else{
                    //获取验证码成功
                    mHandler!!.sendEmptyMessage(Config.SEND_PHONE_CODE)
                }
            }

            override fun onFailure() {
                //处理错误请求
                Snackbar.make(mvpView!!.registerConstraintLayout, R.string.get_phone_code_error, Snackbar.LENGTH_LONG).show()
            }
        })
    }

    init {
        attachView(view, RegisterModelImpl())
    }
}
