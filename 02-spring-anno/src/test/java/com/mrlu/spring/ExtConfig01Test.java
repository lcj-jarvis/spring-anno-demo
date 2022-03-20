package com.mrlu.spring;

import com.mrlu.spring.config.ExtConfig01;
import org.junit.Test;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-03-07 18:07
 */
public class ExtConfig01Test {
    @Test
    public void test01(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ExtConfig01.class);
        context.publishEvent(new ApplicationEvent("我发布的事件") {
            @Override
            public Object getSource() {
                return super.getSource();
            }
        });
        context.close();
    }
}
