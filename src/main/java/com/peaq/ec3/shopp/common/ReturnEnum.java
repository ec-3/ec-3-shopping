package com.peaq.ec3.shopp.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

public interface ReturnEnum {

    @Getter
    @AllArgsConstructor
    enum Login {

        SUCCESS(0,"login successful"),
        FAIL(1,"login failed"),
        PLEASE_SIGN(2,"please sign in"),
        NOT_ACCESS(3,"You do not have access"),
        ;

        private int code;
        private String msg;
    }

}
