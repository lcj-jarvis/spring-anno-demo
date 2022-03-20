package com.mrlu.spring;

import com.mrlu.spring.bean.Dog;
import com.mrlu.spring.config.SpringConfig03;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-03-05 23:23
 */
public class SpringConfig03Test {
    /**
     * 测试bean的生命周期，以及定义初始化方法的第三种方式和后置处理器
     *
     * BeanPostProcessor原理
     * populateBean(beanName, mbd, instanceWrapper);给bean的属性进行赋值
     * initializeBean
     * {
     *     applyBeanPostProcessorsBeforeInitialization(wrappedBean, beanName);
     *     invokeInitMethods(beanName, wrappedBean, mbd);//执行自定义的初始化方法
     *     applyBeanPostProcessorsAfterInitialization(wrappedBean, beanName);
     * }
     */
    @Test
    public void test01(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig03.class);
        System.out.println("容器创建完成。。。。");
        Dog dog = context.getBean("dog", Dog.class);
        System.out.println("获取到bean:"+dog);
        context.close();
    }
}
