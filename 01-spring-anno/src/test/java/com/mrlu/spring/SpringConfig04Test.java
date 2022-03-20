package com.mrlu.spring;

import com.mrlu.spring.bean.User;
import com.mrlu.spring.config.SpringConfig04;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-03-05 23:23
 */
public class SpringConfig04Test {
    @Test
    public void test01(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig04.class);
        User user = context.getBean("user", User.class);
        System.out.println(user);
        //还可以这样获取配置文件里的内容
        ConfigurableEnvironment environment = context.getEnvironment();
        String property = environment.getProperty("User.nickName");
        System.out.println(property);
    }
}
