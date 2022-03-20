package com.mrlu.spring.config;

import com.mrlu.spring.bean.Black;
import com.mrlu.spring.bean.Blue;
import com.mrlu.spring.bean.Red;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.*;
import org.springframework.stereotype.Component;

/**
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-03-07 19:52
 */
@Component
public class MyBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("postProcessBeanFactory....bean的数量："+beanFactory.getBeanDefinitionCount());
    }

    /**
     * BeanDefinitionRegistry Bean定义信息的保存中心，以后BeanFactory
     * 就是按照BeanDefinitionRegistry里面保存的每一个bean定义信息创建bean实例
     */
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(Black.class);
        //注册一个bean【第一种方式】
        registry.registerBeanDefinition("black",rootBeanDefinition);

        BeanDefinitionBuilder definition = BeanDefinitionBuilder.rootBeanDefinition(Red.class);
        AbstractBeanDefinition beanDefinition = definition.getBeanDefinition();
        //注册一个bean【第二种方式】
        registry.registerBeanDefinition("red",beanDefinition);

        System.out.println("postProcessBeanDefinitionRegistry....bean的数量："+registry.getBeanDefinitionCount());
    }
}
