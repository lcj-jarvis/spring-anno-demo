package com.mrlu.spring.bean;

/**
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-03-04 22:42
 */
public class Car {
    private String name;
    public Car() {
        System.out.println("bean的创建");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        System.out.println("bean的赋值完成");
        this.name = name;
    }

    public void init(){
        System.out.println("bean的初始化方法");
    }

    public void destroy(){
        System.out.println("bean的销毁方法");
    }
}
