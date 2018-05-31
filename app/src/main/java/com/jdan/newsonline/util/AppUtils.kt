package com.jdan.newsonline.util

import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager

/**
 * 里面都是静态方法
 * Created by Cxx on 2018/3/21.
 */
object AppUtils {
    private var context: Context?= null

    fun init(context:Context){
        AppUtils.context = context.applicationContext
    }

    fun isAppDebug() : Boolean{
        if (StringUtils.isEmpty(context!!.packageName)) return false
        try {
            val pm = context!!.packageManager as PackageManager
            val ai = pm.getApplicationInfo(context!!.packageName, 0) as ApplicationInfo
            return ai != null && ai!!.flags and ApplicationInfo.FLAG_DEBUGGABLE != 0
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
            return false
        }
    }
    private fun getAppPackageName():String{
        return context!!.packageName
    }

    fun getAppVersionName():String?{
        try{
            return context!!.packageManager.getPackageInfo(getAppPackageName(),0).versionName
        }catch (e:PackageManager.NameNotFoundException){
            e.printStackTrace()
        }
        return null
    }

    /**
     * 得到版本号
     *
     * @param context
     * @return
     */
    fun  getAppVersionCode(): Int {
        var versionCode = 0
        try {
            versionCode = context!!.packageManager.getPackageInfo(
                    getAppPackageName(), 0).versionCode
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }

        return versionCode
    }

}