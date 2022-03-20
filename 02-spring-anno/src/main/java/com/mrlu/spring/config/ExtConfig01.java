package com.mrlu.spring.config;

import com.mrlu.spring.bean.Blue;
import com.mrlu.spring.bean.Red;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-03-07 17:54
 *
 * 扩展原理：
 * BeanPostProcessor：bean后置处理器，bean创建对象初始化前后进行拦截工作的
 * 一、BeanFactoryPostProcessor：beanFactory的后置处理器
 *    在BeanFactory标准初始化之后调用，所有的bean定义语句保存到beanFactory中，
 *    但是Bean的实例还没有被创建
 *
 * 1、ioc创建对象
 * 2、invokeBeanFactoryPostProcessors(beanFactory);
 *    如何找到所有的BeanFactoryPostProcessor并执行他们的方法
 *      （1）直接在BeanFactory中找到所有类型是BeanFactoryPostProcessor的组件，并执行他们的postProcessBeanFactory方法
 *      （2）在初始化创建其他组件【bean】前面执行
 *
 * 二、BeanDefinitionRegistryPostProcessor
 *    是BeanFactoryPostProcessor的子接口
 *      postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry);
 *      在所有bean定义信息将要被加载，bean实例还没创建之前执行
 *
 *      优先于BeanFactoryPostProcessor执行；
 *      利用BeanDefinitionRegistryPostProcessor给容器中再额外添加一些组件
 * 原理：
 *   （1）ioc创建对象
 *   （2）refresh--》invokeBeanFactoryPostProcessors(..)
 *   (3)从容器中获取BeanDefinitionRegistryPostProcessor组件。
 *      1、依次触发所有的postProcessBeanDefinitionRegistry方法
 *      2、再来触发所有的postProcessBeanFactory方法
 *  （4）再来容器中找到BeanFactoryPostProcessor组件，然后依次触发postProcessBeanFactory方法。
 *
 * 三、 ApplicationListener：监听容器中发布的事件。事件驱动模型开发
 *       public interface ApplicationListener<E extends ApplicationEvent> extends EventListener
 *       监听ApplicationListener及其下面的子事件
 *
 *      步骤：
 *      （1）写一个监听器（ApplicationListener实现类）来监听某个事件（ ApplicationEvent及其子类）
 *      （2）把监听器都加到容器中
 *      （3） 只要容器中有相关事件的发布，我们就能监听到这个事件；
 *            ContextRefreshedEvent:容器刷新完成（所有bean都完成创建）会发布这个事件；
 *            ContextClosedEvent：关闭容器会发布这个事件;
 *       (4)发布一个事件
 *            context.publishEvent( ApplicationEvent的实现类)
 *
 * 事件发布的原理：
 * 一、容器刷新完成发布的ContextRefreshedEvent事件
 *    1、容器创建对象，refresh();
 *    2、finishRefresh();容器刷新完成
 * 二、自己发布的时间
 *    【事件发布的流程】
 * 三、容器关闭发布的ContextClosedEvent事件
 *    3、publishEvent(new ContextRefreshedEvent(this));
 *         事件发布的流程：
 * 	（1）获取到事件的多播器（派发器）getApplicationEventMulticaster()
 *         （2）multicastEvent(applicationEvent, eventType);派发事件
 * 	（3）获取到所有的ApplicationListener
 * 		Executor executor = getTaskExecutor();
 * 	     	for (ApplicationListener<?> listener : getApplicationListeners(event, type)) {
 *                    1、如果有Executor，可以支持使用Executor进行异步派发
 *
 *                     Spring提供的。可以在自定义派发器的时候，实现这些接口，支持同步和异步
 *                     AsyncTaskExecutor【异步的】
 *                     SyncTaskExecutor【同步的】
 *
 * 		              2、否则，同步的方式直接执行listener方法：invokeListener(listener, event);
 * 		              拿到listener回调onApplicationEvent(event)方法
 *                }
 *
 * 【事件多播器（派发器）】
 *      1、容器创建对象，调用refresh方法
 *      2、initApplicationEventMulticaster();初始化ApplicationEventMulticaster
 *      3、先去容器中找有没有id="applicationEventMulticaster"的组件
 *      4、如果没有this.applicationEventMulticaster = new SimpleApplicationEventMulticaster(beanFactory);
 *         就创建一个SimpleApplicationEventMulticaster多播器，并且加入到容器中，我们就可以在其他组件要派发事件
 * 	指定注入这个SimpleApplicationEventMulticaster多播器
 *
 * 【容器中有哪些监听器】
 *      （1）容器创建对象，调用refresh方法
 *      （2）registerListeners();
 *           从容器中拿到所有的监听器，把它们注册到ApplicationEventMulticaster中
 * 	  String[] listenerBeanNames = getBeanNamesForType(ApplicationListener.class, true, false);
 * 	  for (String listenerBeanName : listenerBeanNames) {
 * 			getApplicationEventMulticaster().addApplicationListenerBean(listenerBeanName);
 *      }
 *
 * 【事件监听器的第二种方式】
 *    使用@EventListener注解
 *    原理：使用EventListenerMethodProcessor处理器来解析方法上的@EventListener注解
 *             EventListenerMethodProcessor实现了SmartInitializingSingleton接口
 *
 *   SmartInitializingSingleton原理： afterSingletonsInstantiated();在所有的单例bean实例化完后调用
 *     （1）ioc容器创建对象并refresh()
 *     （2）finishBeanFactoryInitialization(beanFactory);初始化剩下的所有单实例bean
 *           （1）先获取所有创建好的单实例bean：getBean();
 * 	   (2) 获取所有创建好的单实例bean，判断是否是SmartInitializingSingleton类型.
 * 	       如果是，就调用afterSingletonsInstantiated方法。
 *
 */
@ComponentScan(value = {"com.mrlu.spring.config","com.mrlu.spring.service"})
@Configuration
public class ExtConfig01 {
    @Bean
    public Blue blue(){
        System.out.println("Blue。。。的bean工厂方法");
        return new Blue();
    }
}
