package com.jdan.newsonline.domain.constants

/**
 * Created by Cxx on 2018/3/21.
 */
object Config {
    const val SPOT_SIGN: String = "."
    const val DOU_SIGN: String = ","
    const val DUN_SIGN: String = "、"
    const val REDUCE_SIGN: String = "-"
    const val SPACE_SIGN: String = " "
    const val SEMICOLON_SIGN: String = ";"
    const val NULL_SIGN: String = ""
    const val COLON_SIGN: String = ":"

//    val PERMISSIONS = arrayOf("android.permission.READ_CONTACTS", "android.permission.READ_PHONE_STATE", "android.permission.CALL_PHONE", "android.permission.CAMERA", "android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_EXTERNAL_STORAGE")
    val PERMISSIONS = arrayOf("android.permission.READ_CONTACTS",  "android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_EXTERNAL_STORAGE")
    const val DEBUG: Boolean  = true
    const val SP_NAME = "config"
    const val FRAGMENT_TAG = "fragment_tag"
    const val FRAGMENT_TAG_NEWS = "fragment_tag_news"
    const val FRAGMENT_TAG_VIDEO = "fragment_tag_video"
    const val FRAGMENT_TAG_NOTICE = "fragment_tag_notice"
    const val FRAGMENT_TAG_MINE = "fragment_tag_mine"
    const val COMMIT_SUCCESS = "commit_success"
    const val GET_SUCCESS = "get_success"





    const val IS_FIRST_LOGIN = "isFirstLogin"//是否第一次登录
    const val IS_LOGIN = "is_login"//是否登录了
    const val WX = "wx"
    const val QQ = "qq"
    const val WB = "wb"
    const val COUNTRY = "86"
   const val SEND_PHONE_CODE: Int = 0x0001
    const val CHANGE_CODE_TIME: Int = 0x0002

    const val UNIQUEREQ = "uniqueReq"
    const val PHONE = "phone"
    const val LOGIN_STYLE = "login_style"
   const val USERNAME = "username"
    const val PASSWORD = "password"
    const val USER_INFO ="user_info"
    const val DEFAULT_PWD = "123456"
}