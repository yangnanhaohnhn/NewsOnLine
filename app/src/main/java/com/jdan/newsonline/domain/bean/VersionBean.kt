package com.jdan.newsonline.domain.bean

class VersionBean : BaseBean() {
     var appUrl: String? = null

     var version: String? = null
//        get() = version
//        set(value) {
//            version = value
//        }

    override fun toString(): String {
        return "VersionBean [appUrl=$appUrl + version = $version]"
    }
}