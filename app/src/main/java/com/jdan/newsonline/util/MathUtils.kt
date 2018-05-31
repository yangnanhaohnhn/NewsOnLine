package com.jdan.newsonline.util

import com.jdan.newsonline.domain.constants.Config

object MathUtils {
    // 格式:10个字符-10个数子从0~99随机排列 (中间用-分开)
    // Xds9ybOh5z-51_41_40_9_59_52_17_23_61_43
    // ID8fs2qEp6-57_5_39_7_40_31_2_54_20_15
    // fN8wsqfD0g-7_49_39_46_40_2_7_5_32_14
    fun getRandomStr():String{
        val arr = arrayOf("G", "T", "q", "j", "7", "D", "t", "f", "n", "9",
                "M", "c", "m", "B", "g", "6", "W", "O", "v", "U", "p", "1",
                "3", "h", "u", "V", "e", "L", "R", "A", "l", "2", "0", "F",
                "Z", "C", "H", "r", "P", "8", "s", "d", "Y", "z", "x", "Q",
                "w", "k", "J", "N", "a", "X", "b", "o", "E", "4", "K", "I",
                "S", "y", "i", "5")
        var result = ""
        var key = ""
        var value = ""

        for (i in 0 until 10){
            var k =  Math.floor(arr.size * Math.random()).toInt()
            if (StringUtils.isEmpty(value)){
                value += k
            }else{
                value += "_"+ k
            }
            key += arr[k]
        }
        result = key +Config.REDUCE_SIGN + value
        return result
    }
}