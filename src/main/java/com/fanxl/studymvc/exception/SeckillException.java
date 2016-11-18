package com.fanxl.studymvc.exception;

/**
 * Created by fanxl2 on 2016/11/18.
 */
public class SeckillException extends RuntimeException {

    public SeckillException(String message) {
        super(message);
    }

    public SeckillException(String message, Throwable cause) {
        super(message, cause);
    }
}
