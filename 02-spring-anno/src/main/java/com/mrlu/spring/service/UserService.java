package com.mrlu.spring.service;

import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-03-07 22:24
 */
@Service
public class UserService {

    @EventListener(value = {ApplicationEvent.class})
    public void event(ApplicationEvent event){
        System.out.println("UserService...收到的事件:"+event);
    }
}
