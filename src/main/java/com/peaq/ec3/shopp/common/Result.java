package com.peaq.ec3.shopp.common;

import com.alibaba.fastjson2.JSON;
import lombok.Data;

@Data
public class Result<T> {
    private int code;
    private String msg;
    private T data;

    public Result() {

    }

    public Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> Result<T> returnFail() {
        return new Result(ReturnMsg.EXCEPTION_CODE, ReturnMsg.EXCEPTION_DESC);
    }

    public static <T> Result<T> returnFail(String msg) {
        return new Result(ReturnMsg.EXCEPTION_CODE, msg);
    }

    public static <T> Result<T> returnFail(T data) {
        return new Result(ReturnMsg.EXCEPTION_CODE, ReturnMsg.EXCEPTION_DESC, data);
    }

    public static <T> Result<T> returnFail(String msg, T data) {
        return new Result(ReturnMsg.EXCEPTION_CODE, msg, data);
    }

    public static <T> Result<T> returnFailReason(String reason) {
        return new Result(ReturnMsg.EXCEPTION_CODE, reason);
    }

    public static <T> Result<T> returnSuccess() {
        return new Result(ReturnMsg.SUCCESS_CODE, ReturnMsg.SUCCESS_DESC);
    }

    public static <T> Result<T> returnSuccess(T data) {
        return new Result(ReturnMsg.SUCCESS_CODE, ReturnMsg.SUCCESS_DESC, data);
    }

    public static <T> Result<T> returnResult(int code, String desc, T data) {
        return new Result(code, desc, data);
    }


    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }


}
