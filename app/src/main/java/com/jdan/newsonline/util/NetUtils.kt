package com.jdan.newsonline.util

import android.content.Context
import android.net.ConnectivityManager

object NetUtils{
    fun isNetWorkConnected(mContext: Context) : Boolean{
        if (mContext != null){
            var mConnectivityManager = mContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            var mNetworkInfo = mConnectivityManager.activeNetworkInfo;
            if (mNetworkInfo != null){
                return mNetworkInfo.isAvailable
            }
        }
        return false
    }

    fun isWifiConnect(mContext: Context):Boolean{
        val connManager = mContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
        if (mWifi.isConnected){
            return true
        }
        return false
    }
}