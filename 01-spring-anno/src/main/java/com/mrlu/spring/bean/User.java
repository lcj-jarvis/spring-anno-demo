package com.mrlu.spring.bean;

import org.springframework.beans.factory.annotation.Value;

/**
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-03-05 23:32
 */
public class User {

    /**
     * 使用@Value赋值：
     * 1、基本数值
     * 2、可以写spring的表达式
     * 3、可以写${配置文件的key}，取出配置文件【properties】中的值（在运行环境变量中的值）
     */
    @Value("张三")
    private String name;
    //相当于age=18
    @Value("#{20-2}")
    private Integer age;
    @Value("${User.nickName}")
    private String nickName;

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", nickName='" + nickName + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
