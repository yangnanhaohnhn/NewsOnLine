package com.jdan.newsonline.domain.bean.inner

import java.io.Serializable
import java.util.*

class UserBean : Serializable{
    var user_id: String? = null
    var user_phone: String? = null
    var user_nick_name: String? = null
    var user_sex: String? = null
    var user_img: String? = null
    var user_qq: String? = null
    var user_chat: String? = null
    var last_login: Date? = null
    var user_islogin: String? = null
    var user_uuid: String? = null
    var user_login_style: String? = null
    var user_img_name: String? = null
    var user_name: String? = null
    override fun toString(): String {
        return "RegisterBean(user_id=$user_id, user_phone=$user_phone, user_nick_name=$user_nick_name, user_sex=$user_sex, user_img=$user_img, user_qq=$user_qq, user_chat=$user_chat, last_login=$last_login, user_islogin=$user_islogin, user_uuid=$user_uuid, user_login_style=$user_login_style, user_img_name=$user_img_name, user_name=$user_name)"
    }
}