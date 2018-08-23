package com.zhuolang.starryserver.exception;

public class MyThrowException extends RuntimeException {
    public MyThrowException(String message) {
        super(message);
    }

    public MyThrowException(String message, Throwable cause) {
        super(message, cause);
    }
}
