package com.huuinn.demo.common;

public class CustomException extends Exception {

    private static final String message = "测试用异常";

    public CustomException() {
        super(message);
    }
}
