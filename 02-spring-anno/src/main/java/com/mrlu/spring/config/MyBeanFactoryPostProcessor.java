package com.mrlu.spring.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-03-07 17:59
 *
 *
 * 1、ioc创建对象
 * 2、invokeBeanFactoryPostProcessors(beanFactory);
 *    如何找到所有的BeanFactoryPostProcessor并执行他们的方法
 *      （1）直接在BeanFactory中找到所有类型是BeanFactoryPostProcessor的组件，并执行他们的postProcessBeanFactory方法
 *      （2）在初始化创建其他组件【bean】前面执行
 */
@Component
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("MyBeanFactoryPostProcessor...postProcessBeanFactory");
        int count = beanFactory.getBeanDefinitionCount();
        System.out.println("当前beanFactory中有"+count+"个bean");
        String[] names = beanFactory.getBeanDefinitionNames();
        for (String name:names) {
            System.out.println(name);
        }
    }
}
