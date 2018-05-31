package com.jdan.newsonline.widget.retrofit

import com.jdan.newsonline.domain.bean.VersionBean
import com.jdan.newsonline.domain.constants.Config
import com.jdan.newsonline.domain.constants.UrlFactory
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

interface ApiStores {
    @GET(UrlFactory.CHECK_VERSION_URL)
    fun checkVersionReq(@Query(Config.UNIQUEREQ) uniqueReq: String): Observable<VersionBean>
}