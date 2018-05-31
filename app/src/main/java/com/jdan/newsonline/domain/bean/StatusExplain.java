package com.jdan.newsonline.domain.bean;

public enum StatusExplain {
    //成功
    SUCESS (0,"成功"),

    //查询无记录
    SELECT_NO_RES(2,"查询无记录"),

    //请求参数错误
    REQ_PARAM_ERROR(3,"请求参数错误"),

    //非法请求
    NOT_REQ(4,"非法请求");

    private int code;
    private String name;
    private StatusExplain(int code,String name){
        this.code = code;
        this.name=name;
    }
    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public static String getItemByCode(int code){
        for (StatusExplain value : StatusExplain.values()) {
            if (value.code == code) {
                return value.getName();
            }
        }
        return null;
    }
}
