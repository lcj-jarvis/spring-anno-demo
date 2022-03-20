package com.mrlu.springmvc.service;

import org.springframework.stereotype.Service;

/**
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-03-09 22:19
 */
@Service
public class HelloService {
    public String sayHello(String context){
        return "Hello " + context;
    }
}
