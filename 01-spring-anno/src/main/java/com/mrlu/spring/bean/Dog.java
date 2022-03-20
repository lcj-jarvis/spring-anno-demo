package com.mrlu.spring.bean;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-03-04 23:51
 * 实现ApplicationContextAware，重写setApplicationContext方法
 * 就可以获取到ApplicationContext
 */
public class Dog implements ApplicationContextAware {
    private ApplicationContext context;
    private String name;
    public Dog() {
        System.out.println("============Dogbean的创建=============");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        System.out.println("=============Dogbean的赋值完成============");
        this.name = name;
    }

    @PostConstruct
    public void init(){
        System.out.println("==========Dogbean的初始化方法=========");
    }

    @PreDestroy
    public void destroy(){
        System.out.println("===========Dogbean的销毁方法============");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }
}
