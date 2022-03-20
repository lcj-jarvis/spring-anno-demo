package com.mrlu.spring;

import com.mrlu.spring.bean.Book;
import com.mrlu.spring.bean.Car;
import com.mrlu.spring.bean.Dog;
import com.mrlu.spring.bean.Person;
import com.mrlu.spring.config.SpringConfig02;
import com.mrlu.spring.config.SpringConfig03;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.Map;

/**
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-03-04 18:19
 */
public class SpringConfig02Test {
    @Test
    public void test01(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig02.class);
        System.out.println("ioc容器创建完成。。。");
        Person person01 = context.getBean("person", Person.class);
        Person person02 = context.getBean("person", Person.class);
        System.out.println(person01==person02);
    }

    @Test
    public void test02(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig02.class);
        ConfigurableEnvironment environment = context.getEnvironment();
        //获取操作系统的名字
        String property = environment.getProperty("os.name");
        System.out.println(property);
        String[] beans = context.getBeanNamesForType(Person.class);
        for (String name:beans) {
            System.out.println(name);
        }
        Map<String, Person> beansOfType = context.getBeansOfType(Person.class);
        System.out.println(beansOfType);
    }

    /**
     * 测试@Import注解
     */
    @Test
    public void test03(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig02.class);
        String[] names = context.getBeanDefinitionNames();
        for (String name:names) {
            System.out.println(name);
        }
    }

    /**
     * 测试定义FactoryBean工厂的方式注入bean
     */
    @Test
    public void test04(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig02.class);
        Object bean = context.getBean("colorFactoryBean");
        //com.mrlu.spring.bean.Color@7c0c77c7
        System.out.println(bean);
        Object bean1 = context.getBean("&colorFactoryBean");
        System.out.println("获取到当前对应的工厂bean:"+ bean1);

    }

    /**
     * 测试bean的生命周期，以及定义初始化方法的第一种方式
     */
    @Test
    public void test05(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig02.class);
        System.out.println("容器创建完成。。。。");
        Car car = context.getBean("car", Car.class);
        System.out.println("获取到bean:"+car);
        context.close();
    }

    /**
     * 测试bean的生命周期，以及定义初始化方法的第二种方式
     */
    @Test
    public void test06(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig02.class);
        System.out.println("容器创建完成。。。。");
        Book book = context.getBean("book", Book.class);
        System.out.println("获取到bean:"+book);
        context.close();
    }


}
