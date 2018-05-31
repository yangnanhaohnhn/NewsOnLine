package com.jdan.newsonline.util

/**
 * Created by Cxx on 2018/3/21.
 */
object StringUtils {
    private fun StringUtils(){
        throw AssertionError()
    }

    /**
     * get length of CharSequence
     *
     * <pre>
     * length(null) = 0;
     * length(\"\") = 0;
     * length(\"abc\") = 3;
    </pre> *
     *
     * @param str
     * @return if str is null or empty, return 0, else return
     * [CharSequence.length].
     */
    fun length(str: CharSequence?): Int {
        return str?.length ?: 0
    }

    /**
     * is null or its length is 0
     *
     * <pre>
     * isEmpty(null) = true;
     * isEmpty(&quot;&quot;) = true;
     * isEmpty(&quot;  &quot;) = false;
    </pre> *
     *
     * @param str
     * @return if string is null or its size is 0, return true, else return
     * false.
     */
    fun isEmpty(str: CharSequence?): Boolean {
        return str == null || str.length == 0 || "".equals(str)
    }

    fun isEquals(actual:String,expected:String):Boolean{
        return actual === expected || (if (actual == null) expected == null else actual == expected)
    }
}