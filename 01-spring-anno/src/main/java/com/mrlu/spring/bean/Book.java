package com.mrlu.spring.bean;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-03-04 23:09
 */
public class Book implements InitializingBean, DisposableBean {
    private String name;

    public Book() {
        System.out.println("BookBean创建完成");
    }

    public Book(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        System.out.println("BookBean的赋值完成");
        this.name = name;
    }

    /**
     * Invoked by the containing {@code BeanFactory} on destruction of a bean.
     * 原接口中的描述。意思是bean销毁之后执行
     * @throws Exception
     */
    @Override
    public void destroy() throws Exception {
        System.out.println("BookBean的销毁方法");
    }

    /**
     * Invoked by the containing {@code BeanFactory} after it has set all bean properties
     * and satisfied {@link BeanFactoryAware}, {@code ApplicationContextAware} etc.
     * 原接口中的描述，意识是赋值完bean的所有属性之后，才会执行
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("BookBean的初始化方法");
    }
}
