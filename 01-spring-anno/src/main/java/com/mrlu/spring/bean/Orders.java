package com.mrlu.spring.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.stereotype.Component;
import org.springframework.util.StringValueResolver;

/**
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-03-06 14:12
 *
 * 自定义组件想要使用Spring容器底层的一些组件
 */
@Component
public class Orders implements ApplicationContextAware, BeanNameAware, EmbeddedValueResolverAware {

    private ApplicationContext context;
    @Override
    public void setBeanName(String name) {
        System.out.println("当前bean的名字："+name);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("传入的ioc容器："+applicationContext);
        this.context = applicationContext;
    }

    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        String stringValue = resolver.resolveStringValue("你好${os.name},我是#{20-18}");
        System.out.println(stringValue);
    }
}
