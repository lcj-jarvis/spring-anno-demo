package com.mrlu.spring;

import com.mrlu.spring.bean.Person;
import com.mrlu.spring.config.SpringConfig01;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-03-04 17:09
 */
public class SpringConfig01Test {

    @Test
    public void test01(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig01.class);
        //Person person = context.getBean("person01",Person.class);
        Person person = context.getBean("person",Person.class);
        System.out.println(person);
        String[] namesForType = context.getBeanNamesForType(Person.class);
        for (String name:namesForType) {
            //获取bean的id值
            System.out.println(name);
        }
    }

    @Test
    public void test02(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig01.class);
        String[] names = context.getBeanDefinitionNames();
        for (String name:names) {
            System.out.println(name);
        }
    }

    @Test
    public void test03(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig01.class);
        SpringConfig01 springConfig = context.getBean("mySpringConfig", SpringConfig01.class);
        System.out.println(springConfig);
   //     Person person = springConfig.person01();
        Person person01 = context.getBean("person", Person.class);
   //     System.out.println(person==person01);

    }
}
