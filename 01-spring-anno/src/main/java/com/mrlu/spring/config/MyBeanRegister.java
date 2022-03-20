package com.mrlu.spring.config;

import com.mrlu.spring.bean.RainBow;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-03-04 21:56
 */
public class MyBeanRegister implements ImportBeanDefinitionRegistrar {
    /**
     *
     * @param importingClassMetadata AnnotationMetadata类型，可以获取当前类的注解信息即其他信息
     * @param registry  BeanDefinitionRegistry注册类
     *                  把所有需要添加到容器中的bean，调用
     *                  BeanDefinitionRegistry.registerBeanDefinition()方法，手工注册进来
     */
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        boolean definition = registry.containsBeanDefinition("com.mrlu.spring.bean.Red");
        boolean definition1 = registry.containsBeanDefinition("com.mrlu.spring.bean.Blue");
        if (definition && definition1){
            //指定Bean定义的信息：如Bean的类型
            RootBeanDefinition beanDefinition = new RootBeanDefinition(RainBow.class);
            //注册一个Bean，指定bean名，方法的第一个参数就是bean的别名【可以理解成id值】
            registry.registerBeanDefinition("rainBow",beanDefinition);
        }
    }
}
