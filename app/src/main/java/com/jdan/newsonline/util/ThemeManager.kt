package com.jdan.newsonline.util

import android.content.Context
import android.content.res.Resources
import java.util.*
import kotlin.collections.HashMap

object ThemeManager {
    // 默认是日间模式
    private var mThemeMode = ThemeMode.DAY
    // 主题模式监听器
    private var  mThemeChangeListenerList = LinkedList<OnThemeChangeListener>()
    // 夜间资源的缓存，key : 资源类型, 值<key:资源名称, value:int值>
    private var  sCachedNightRes = HashMap<String, HashMap<String, Int>>()
    // 夜间模式资源的后缀，比如日件模式资源名为：R.color.activity_bg, 那么夜间模式就为 ：R.color.activity_bg_night
    private const val RESOURCE_SUFFIX = "_night"

    /**
     * s=设置主题模式
     */
    fun setThemeMode(themeMode:ThemeMode){
        if (mThemeMode!= themeMode){
            mThemeMode = themeMode
            if (mThemeChangeListenerList.size > 0){
                for (listener in mThemeChangeListenerList){
                    listener.onThemeChanged()
                }
            }
        }
    }

    fun getCurThemeRe(context:Context,resId:Int):Int {

        if (getThemeMode() == ThemeMode.DAY) {
            return resId
        }
        //资源名
        var entryName = context.resources.getResourceEntryName(resId)
        //资源类型
        var typeName = context.resources.getResourceTypeName(resId)
        var cachedRes = sCachedNightRes[typeName] as HashMap<String, Int>
        if (cachedRes != null && resId != 0) {
            return resId
        } else {
            //如果缓存中没有再根据资源id去动态获取
            try {
                // 通过资源名，资源类型，包名得到资源int值
                var nightResId = context.resources.getIdentifier(entryName + RESOURCE_SUFFIX, typeName, context.packageName) as Int
                // 放入缓存中
                cachedRes!![entryName + RESOURCE_SUFFIX] = nightResId
                sCachedNightRes[typeName] = cachedRes
                return nightResId
            } catch (e: Resources.NotFoundException ) {
                e.printStackTrace()
            }
        }
        return 0
    }

    fun registerThemeChangeListener(listener:OnThemeChangeListener){
        if (!mThemeChangeListenerList.contains(listener))
            mThemeChangeListenerList.add(listener)
    }
    fun unRegisterThemeChangeListener(listener:OnThemeChangeListener){
        if (!mThemeChangeListenerList.contains(listener))
            mThemeChangeListenerList.remove(listener)
    }

    /**
     * 获取主题模式
     */
    fun getThemeMode():ThemeMode{
        return mThemeMode
    }

    enum class ThemeMode{
        DAY,NIGHT
    }

    interface OnThemeChangeListener{
        //主题切换时回调
        fun onThemeChanged()
    }
}