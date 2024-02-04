package com.peaq.ec3.shopp.common;

import com.alibaba.fastjson2.JSON;
import lombok.Data;

@Data
public class Result<T> {
    private String code;
    private String msg;
    private T data;

    public Result() {

    }

    public Result(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
    
    public void setSuccessData(T data) {
        this.code = ReturnCode.CODE_0;
        this.msg = ReturnCode.DESC_0;
        this.data = data;
    }

    public void setFailData(T data) {
        this.code = ReturnCode.CODE_9;
        this.msg = ReturnCode.DESC_9;
        this.data = data;
    }

    public void setTimeOutData(T data) {
        this.code = ReturnCode.CODE_5;
        this.msg = ReturnCode.DESC_5;
        this.data = data;
    }
    public static <T> Result<T> returnFail() {
        return new Result(ReturnCode.CODE_9, ReturnCode.DESC_9);
    }

    public static <T> Result<T> returnFail(String msg) {
        return new Result(ReturnCode.CODE_9, msg);
    }

    public static <T> Result<T> returnFail(T data) {
        return new Result(ReturnCode.CODE_9, ReturnCode.DESC_9, data);
    }

    public static <T> Result<T> returnFail(String msg, T data) {
        return new Result(ReturnCode.CODE_9, msg, data);
    }

    public static <T> Result<T> returnFailReason(String reason) {
        return new Result(ReturnCode.CODE_9, reason);
    }

    public static <T> Result<T> returnSuccess() {
        return new Result(ReturnCode.CODE_0, ReturnCode.DESC_0);
    }

    public static <T> Result<T> returnSuccess(String msg) {
        return new Result(ReturnCode.CODE_0, msg);
    }

    public static <T> Result<T> returnSuccess(T data) {
        return new Result(ReturnCode.CODE_0, ReturnCode.DESC_0, data);
    }

    public static <T> Result<T> returnResult(String code, String desc, T data) {
        return new Result(code, desc, data);
    }

    public static String returnTimeOut() {
        return new Result(ReturnCode.CODE_5, ReturnCode.DESC_5).toString();
    }

    public static <T> Result<T> returnTimeOut(T data) {
        return new Result(ReturnCode.CODE_5, ReturnCode.DESC_5, data);
    }
    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

    public static <T> Result<T> sessionError(String msg){
        return new Result(ReturnCode.ERROR_SESSION, msg);
    }

    public static <T> Result<T> freezeAccount(String msg){
        return new Result(ReturnCode.FREEZE_ACCOUNT, msg);
    }
}
