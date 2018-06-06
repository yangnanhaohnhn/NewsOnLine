package com.jdan.newsonline.util

import com.google.gson.Gson

object JsonUtils {

    fun createJsonStr(obj:Any):String{
        return Gson().toJson(obj)
    }

    fun parseJson(jsonStr:String,clazz: Class<*>): Any? {
        return Gson().fromJson(jsonStr,clazz)
    }
}