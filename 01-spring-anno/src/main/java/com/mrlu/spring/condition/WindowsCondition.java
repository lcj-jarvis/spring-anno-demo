package com.mrlu.spring.condition;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-03-04 21:01
 *
 * 如果是windows系统给容器注册("bill")
 */
public class WindowsCondition implements Condition {
    /**
     *
     * @param context ConditionContext类型，判断条件能使用上下文（环境）
     * @param metadata AnnotatedTypeMetadata类型，能获取注释信息
     * @return 是否为window是系统。如果是返回true，否则返回false
     */
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        //1、能获取到ioc使用到的beanfactory
        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
        //2、获取类加载器
        ClassLoader classLoader = context.getClassLoader();
        //3、获取当前环境信息
        Environment environment = context.getEnvironment();
        //4、获取bean定义的注册类
        BeanDefinitionRegistry registry = context.getRegistry();
        //可以判断容器中注册bean的情况，也可以给容器中注册bean
        boolean person = registry.containsBeanDefinition("person");
        String property = environment.getProperty("os.name");
        return property.contains("Windows");
    }
}
