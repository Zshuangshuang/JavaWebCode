package org.example.exception;

import lombok.Getter;

/**
 * Author:ZouDouble
 * Description:
 * 天气：晴天
 * 目标：Good Offer
 * Date    2021-01-24 17:47
 */
@Getter
public class AppException extends RuntimeException{
    private String code;
    public AppException(String code,String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public AppException(String code,String message) {
       /* super(message);
        this.code = code;*/
        this(code,message,null);
    }
}
