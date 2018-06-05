package com.jdan.newsonline.widget.retrofit

import com.jdan.newsonline.domain.bean.RegisterBean
import com.jdan.newsonline.domain.bean.VersionBean
import com.jdan.newsonline.domain.constants.Config
import com.jdan.newsonline.domain.constants.UrlFactory
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

interface ApiStores {
    @GET(UrlFactory.CHECK_VERSION_URL)
    fun checkVersionReq(@Query(Config.UNIQUEREQ) uniqueReq: String): Observable<VersionBean>

    @GET(UrlFactory.REGISTER_USER_URL)
    fun registerUserReq(@Query(Config.PHONE) phoneStr: String, @Query(Config.LOGIN_STYLE) loginStyle: String, @Query(Config.UNIQUEREQ) randomStr: String): Observable<RegisterBean>
}