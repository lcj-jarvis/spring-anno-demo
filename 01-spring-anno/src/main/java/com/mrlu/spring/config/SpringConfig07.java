package com.mrlu.spring.config;

import com.mrlu.spring.aop.LogAspect;
import com.mrlu.spring.aop.MathCalculator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-03-06 15:36
 *
 * AOP:动态代理
 *     指的是在程序运行期间动态的将某段代码添加到指定方法指定位置进行运行的编程方式
 *
 * 1、导入aop模块：Spring AOP（spring-aop）
 * 2、定义一个业务逻辑类（MathCalculator）;在业务逻辑运行的时候将日志进行打印（方法之前，方法运行时，方法出现异常）
 * 3、定于一个切面类LogAspect：切面类里的方法，动态的感知MathCalculator的div方法运行到哪了
 *    通知方法
 *    前置通知(@Before)
 *    后置通知(@After)
 *    返回通知(@AfterReturning)
 *    异常通知(@AfterThrowing)
 *    环绕通知(@Around)
 * 4、给切面类的目标方法标注何时何地运行（通知注解）
 * 5、将切面类和业务逻辑类（目标方法所在类）都加入到容器中
 * 6、必须告诉Spring哪个类是切面类（给切面类加一个注解：@Aspect）
 * 7、给配置类中加@EnableAspectJAutoProxy【开启基于注解的aop模式】
 *    在spring中很多@EnableXXX;
 *
 *  分三步：
 *  1、将业务逻辑组件和切面类都加入到容器中，告诉Spring哪个是切面类(@Aspect)
 *  2、在切面类上的每一个通知方法上标注通知注解，告诉Spring何时何地运行（切入点表达式）
 *  3、开启基于注解的aop模式： @EnableAspectJAutoProxy
 *
 *
 *
 *  AOP原理【看给容器注册了什么组件，这个组件什么时候工作，这个组件的功能是什么？】
 *  1、@EnableAspectJAutoProxy的注解上面有：
 *     @Import(AspectJAutoProxyRegistrar.class)：给容器导入AspectJAutoProxyRegistrar
 *     class AspectJAutoProxyRegistrar implements ImportBeanDefinitionRegistrar {...}
 *     利用AspectJAutoProxyRegistrar自定义给容器中注册bean
 *     【bean的名字】internalAutoProxyCreator=AnnotationAwareAspectJAutoProxyCreator【bean的定义】
 *     给容器注册一个AnnotationAwareAspectJAutoProxyCreator
 *
 *  2、AnnotationAwareAspectJAutoProxyCreator【实现了Order接口】
 *  AnnotationAwareAspectJAutoProxyCreator
 *       --继承--》AspectJAwareAdvisorAutoProxyCreator
 *                   --继承--》AbstractAdvisorAutoProxyCreator
 *                         --继承--》AbstractAutoProxyCreator
 *                                      --继承--》 ProxyProcessorSupport
 * 		                                --实现--》SmartInstantiationAwareBeanPostProcessor, BeanFactoryAware
 * 		                                    关注后置处理器【在bean初始化前后做的事情】、自动装配BeanFactory
 *
 *  从父类开始往子类 关注后置处理器【在bean初始化前后做的事情】、自动装配BeanFactory 相关的方法
 *       1、
 *      AbstractAutoProxyCreator.setBeanFactory(BeanFactory beanFactory)方法
 *      AbstractAutoProxyCreator中有后置处理器的逻辑
 *         AbstractAutoProxyCreator.postProcessBeforeInstantiation(Class<?> beanClass, String beanName)
 *         AbstractAutoProxyCreator.postProcessAfterInitialization(@Nullable Object bean, String beanName)
 *
 *      2、
 *      AbstractAdvisorAutoProxyCreator.setBeanFactory(BeanFactory beanFactory)【重写了AbstractAutoProxyCreator的】
 *         --该方法里面调用了--》 initBeanFactory(..)方法
 *
 *      3、重写了AbstractAdvisorAutoProxyCreator的initBeanFactory方法
 *      AnnotationAwareAspectJAutoProxyCreator.initBeanFactory(ConfigurableListableBeanFactory beanFactory)
 *
 *   流程：
 *   1、传入配置类，创建ioc容器
 *   2、注册配置类，调用refresh()方法刷新容器
 *   3、调用registerBeanPostProcessors(beanFactory)方法：注册bean的后置处理器来方便拦截bean的创建
 *      （1）先获取ioc容器中已经定义了的需要声明创建对象的所有BeanPostProcessor
 *      （2）给容器中加别的BeanPostProcessor
 *      （3）优先注册PriorityOrdered接口的BeanPostProcessor
 *      （4）再给容器中注册实现了Ordered接口的BeanPostProcessor
 *      （5）注册没实现优先级接口的BeanPostProcessor
 *      （6）注册BeanPostProcessor，实际上是创建BeanPostProcessor对象，保存到容器中
 *           创建internalAutoProxyCreator的BeanPostProcessor【实际上是AnnotationAwareAspectJAutoProxyCreator】
 *          （1）创建Bean的实例
 *          （2）populateBean(beanName, mbd, instanceWrapper);给bean的各种属性赋值
 *          （3）initializeBean(beanName, exposedObject, mbd);初始化bean：
 *                  （I）invokeAwareMethods(beanName, bean);处理Aware接口的方法回调
 *                  （II）applyBeanPostProcessorsBeforeInitialization(wrappedBean, beanName);应用后置处理器的BeforeInitialization方法
 *                  （II）invokeInitMethods(beanName, wrappedBean, mbd);执行自定义的初始化方法
 *                  （VI）applyBeanPostProcessorsAfterInitialization(wrappedBean, beanName);执行后置处理器的AfterInitialization方法
 *          （4）BeanPostProcessor【实际上是AnnotationAwareAspectJAutoProxyCreator】创建成功 ---》aspectJAdvisorsBuilder
 *       (7)把BeanPostProcessor注册到BeanFactory中
 *          beanFactory.addBeanPostProcessor(postProcessor);
 *   =====================================以上是创建和注册AnnotationAwareAspectJAutoProxyCreator的过程======================================
 */
@EnableAspectJAutoProxy
@Configuration
public class SpringConfig07 {

    @Bean
    public MathCalculator mathCalculator(){
        return  new MathCalculator();
    }

    @Bean
    public LogAspect logAspect(){
        return  new LogAspect();
    }

}
