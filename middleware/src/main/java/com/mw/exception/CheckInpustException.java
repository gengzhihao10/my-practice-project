package com.mw.exception;

/**
 * @author gengzhihao
 * @date 2022/10/24 9:49
 * @description 校验入参非空异常
**/

public class CheckInpustException extends RuntimeException{
    private static final long serialVersionUID = 2291942107749094272L;

    public CheckInpustException() {
    }

    public CheckInpustException(String message) {
        super(message);
    }

    public CheckInpustException(String message, Throwable cause) {
        super(message, cause);
    }
}
