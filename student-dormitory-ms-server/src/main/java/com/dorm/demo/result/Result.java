package com.dorm.demo.result;

//Result 类是为了构造 response，主要是响应码。
public class Result {
    //响应码
    private int code;

    private String name;

    public Result(int code) {
        this.code = code;
    }
    public Result(int code , String name) {
        this.code = code;
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }
}

