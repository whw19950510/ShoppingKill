package com.web.exception;

/*
链接已经关闭不能再进行秒杀
 */
public class SecKillCloseException extends SeckillException {
    public SecKillCloseException(String message) {
        super(message);
    }

    public SecKillCloseException(String message, Throwable cause) {
        super(message, cause);
    }
}
