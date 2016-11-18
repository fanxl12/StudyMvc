package com.fanxl.studymvc.exception;

/**
 * 重复秒杀异常(运行时异常)
 * Created by fanxl2 on 2016/11/18.
 */
public class RepeatKillException extends SeckillException {

    public RepeatKillException(String message) {
        super(message);
    }

    public RepeatKillException(String message, Throwable cause) {
        super(message, cause);
    }
}
