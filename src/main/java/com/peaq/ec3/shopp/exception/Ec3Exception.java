package com.peaq.ec3.shopp.exception;


public class Ec3Exception extends RuntimeException {

    protected Integer code;
    protected String message;

    public Ec3Exception(String message) {
        super(message);
        this.message = message;
    }

    public Ec3Exception(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public Ec3Exception(Integer code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
        this.message = message;
    }
    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
