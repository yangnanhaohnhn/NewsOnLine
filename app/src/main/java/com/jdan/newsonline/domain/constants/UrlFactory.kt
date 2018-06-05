package com.jdan.newsonline.domain.constants

/**
 * Created by Cxx on 2018/3/21.
 */
object UrlFactory {
    const val BASE_URL : String = "http://172.22.74.34:8080/" //外网

//    private const val CONSTANTURL : String = "ABDServer/servlet/"
    private const val CONSTANTURL : String = "NewsOnLine/"


    /**
     * 登陆url
     */
    const val LOGIN_URL = CONSTANTURL + "UserServer?command=login"

    /**
     * 检查当前的版本号
     */
    const val CHECK_VERSION_URL = CONSTANTURL + "VersionServer?command=checkCurVersion"

    /**
     * 注册用户
     */
    const val REGISTER_USER_URL = CONSTANTURL + "LoginServer?command=register"

}