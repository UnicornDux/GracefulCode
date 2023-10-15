package com.zheye.column.rest.response;

import lombok.Data;

public class BaseResponse<T> {

    private final Integer code;
    private String msg;
    private T data;

    public static <T> BaseResponse<T> fail(Integer code, String message) {
        return new BaseResponse<>(code, message);
    }

    public static <T> BaseResponse<T> success(T t) {
        return new BaseResponse<>(t);
    }

    private BaseResponse(Integer code, String message) {
       this.code = code;
       this.msg = message;
    }

    private BaseResponse(T t){
        this.code = 0;
        this.data = t;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
