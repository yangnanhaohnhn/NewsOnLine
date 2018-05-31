package com.jdan.newsonline.domain.bean

import java.io.Serializable

/**
 * 基本Bean
 */
open class BaseBean : Serializable{
    var status: Int = 0
    override fun toString(): String {
        return "StatesBean [status=$status]"
    }
}