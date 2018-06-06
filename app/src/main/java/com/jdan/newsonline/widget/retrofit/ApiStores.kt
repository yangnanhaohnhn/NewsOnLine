package com.jdan.newsonline.widget.retrofit

import com.jdan.newsonline.domain.bean.BaseBean
import com.jdan.newsonline.domain.bean.VersionBean
import com.jdan.newsonline.domain.bean.outer.UserBeanOut
import com.jdan.newsonline.domain.constants.Config
import com.jdan.newsonline.domain.constants.UrlFactory
import retrofit2.http.*
import rx.Observable

interface ApiStores {
    @GET(UrlFactory.CHECK_VERSION_URL)
    fun checkVersionReq(@Query(Config.UNIQUEREQ) uniqueReq: String): Observable<VersionBean>

    @GET(UrlFactory.REGISTER_USER_URL)
    fun registerUserReq(@Query(Config.PHONE) phoneStr: String, @Query(Config.LOGIN_STYLE) loginStyle: String, @Query(Config.UNIQUEREQ) randomStr: String): Observable<BaseBean>

    @POST(UrlFactory.LOGIN_URL)
    fun loginReq(@QueryMap param: Map<String, String>, @Query(Config.UNIQUEREQ) randomStr: String): Observable<UserBeanOut>

    @GET(UrlFactory.REGISTER_USER_URL)
    fun modifyPwdReq(@Query(Config.PHONE) phoneStr: String, @Query(Config.LOGIN_STYLE) loginStyle: String, @Query(Config.UNIQUEREQ) randomStr: String)
}