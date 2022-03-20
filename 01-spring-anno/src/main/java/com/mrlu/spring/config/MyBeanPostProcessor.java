package com.mrlu.spring.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-03-05 0:22
 *
 * 后置处理器，初始化前后进行处理工作
 *
 */
@Component
public class MyBeanPostProcessor implements BeanPostProcessor {
    /**
     * 在初始化方法执行之前执行
     * @param bean 创建的bean实例
     * @param beanName bean的名字
     *      返回bean实例，或者根据需要返回包装的bean实例
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("在初始化方法之前执行。。。");
        return bean;
    }

    /**
     * 在初始化方法执行之后执行
     * @param bean 创建的bean实例
     * @param beanName bean的名字
     *      返回bean实例，或者根据需要返回包装的bean实例
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("在初始化方法之后执行。。。");
        return bean;
    }
}
