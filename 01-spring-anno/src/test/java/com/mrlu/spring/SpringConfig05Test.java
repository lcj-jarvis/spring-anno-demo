package com.mrlu.spring;

import com.mrlu.spring.bean.Boss;
import com.mrlu.spring.bean.Employee;
import com.mrlu.spring.bean.Orders;
import com.mrlu.spring.bean.Person;
import com.mrlu.spring.config.SpringConfig01;
import com.mrlu.spring.config.SpringConfig05;
import com.mrlu.spring.dao.PersonDao;
import com.mrlu.spring.service.PersonService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-03-06 12:38
 */
public class SpringConfig05Test {
    /**
     * 测试自动注入
     */
    @Test
    public void test01(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig05.class);
        PersonService personService = context.getBean("personService", PersonService.class);
        System.out.println(personService);
        //PersonDao personDao = context.getBean("personDao2", PersonDao.class);
        //System.out.println(personDao);
    }

    /**
     * 测试@Autowired注解放在:构造器，参数，方法，属性位置上
     */
    @Test
    public void test02(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig05.class);
        //Boss boss = context.getBean("boss", Boss.class);
        Boss boss = context.getBean("boss1", Boss.class);
        System.out.println(boss);
        Employee employee = context.getBean("employee", Employee.class);
        System.out.println(employee);
    }

    /**
     * 自定义组件想要使用Spring容器底层的一些组件（ApplicationContext，BeanFactory....等等）
     *     自定义组件实现xxxAware：在创建对象的时候，会调用接口规定的方法注入相关组件：Aware
     *     把Spring底层的一些组件注入到自定义的Bean中：
     *     xxxAware：功能使用xxxProcessor
     *     ApplicationContextAware===》ApplicationContextAwareProcessor
     */
    @Test
    public void test03(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig05.class);
        Orders orders = context.getBean("orders", Orders.class);
        System.out.println(orders);
    }

    /*
    原理：
    class ApplicationContextAwareProcessor implements BeanPostProcessor {

        @Override
        @Nullable
        public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
            if (!(bean instanceof EnvironmentAware || bean instanceof EmbeddedValueResolverAware ||
                    bean instanceof ResourceLoaderAware || bean instanceof ApplicationEventPublisherAware ||
                    bean instanceof MessageSourceAware || bean instanceof ApplicationContextAware)){
                return bean;
            }

            AccessControlContext acc = null;

            if (System.getSecurityManager() != null) {
                acc = this.applicationContext.getBeanFactory().getAccessControlContext();
            }

            if (acc != null) {
                AccessController.doPrivileged((PrivilegedAction<Object>) () -> {
                    invokeAwareInterfaces(bean);
                    return null;
                }, acc);
            }
            else {
                invokeAwareInterfaces(bean);
            }

            return bean;
        }
        //设置相关的xxxAware的方法参数
        private void invokeAwareInterfaces(Object bean) {
            if (bean instanceof EnvironmentAware) {
                ((EnvironmentAware) bean).setEnvironment(this.applicationContext.getEnvironment());
            }
            if (bean instanceof EmbeddedValueResolverAware) {
                ((EmbeddedValueResolverAware) bean).setEmbeddedValueResolver(this.embeddedValueResolver);
            }
            if (bean instanceof ResourceLoaderAware) {
                ((ResourceLoaderAware) bean).setResourceLoader(this.applicationContext);
            }
            if (bean instanceof ApplicationEventPublisherAware) {
                ((ApplicationEventPublisherAware) bean).setApplicationEventPublisher(this.applicationContext);
            }
            if (bean instanceof MessageSourceAware) {
                ((MessageSourceAware) bean).setMessageSource(this.applicationContext);
            }
            if (bean instanceof ApplicationContextAware) {
                ((ApplicationContextAware) bean).setApplicationContext(this.applicationContext);
            }
        }
	}
     */

}
