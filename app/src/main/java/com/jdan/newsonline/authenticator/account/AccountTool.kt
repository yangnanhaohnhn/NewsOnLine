package com.jdan.newsonline.authenticator.account

/**
 * 账户相关工具
 */
class AccountTool {
    companion object {
        private var INSTANCE : AccountTool? =null
        fun getInstance():AccountTool{
             if (INSTANCE == null){
                 INSTANCE = AccountTool()
             }
            return INSTANCE as AccountTool
         }
    }
}