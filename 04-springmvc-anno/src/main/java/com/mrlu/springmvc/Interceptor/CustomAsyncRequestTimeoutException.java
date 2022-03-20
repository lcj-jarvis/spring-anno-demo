package com.mrlu.springmvc.Interceptor;

/**
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-03-10 13:53
 * 自定义超时异常类
 */
public class CustomAsyncRequestTimeoutException extends RuntimeException {

    private static final long serialVersionUID = 8754629185999484614L;

    public CustomAsyncRequestTimeoutException(String uri){
        super(uri);
    }

}