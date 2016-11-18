package com.fanxl.studymvc.exception;

/**
 * Created by fanxl2 on 2016/11/18.
 */
public class SeckillCloseException extends SeckillException {

    public SeckillCloseException(String message) {
        super(message);
    }

    public SeckillCloseException(String message, Throwable cause) {
        super(message, cause);
    }
}
