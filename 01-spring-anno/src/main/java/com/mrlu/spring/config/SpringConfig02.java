package com.mrlu.spring.config;

import com.mrlu.spring.bean.*;
import com.mrlu.spring.condition.LinuxCondition;
import com.mrlu.spring.condition.WindowsCondition;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Component;


/**
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-03-04 18:08
 *
 */
//满足当前条件，这个类中配置的所有bean注册才会生效【如果里面的bean没有使用其他的@Conditional条件】
//@Conditional({WindowsCondition.class})
@Configuration
//ioc容器会注入Red对象和Blue对象和MyImportSelector的selectImports方法返回的类型数组里的所有对象
// 以及在MyBeanRegister中手工注册的bean
@Import({Red.class, Blue.class,MyImportSelector.class,MyBeanRegister.class})
public class SpringConfig02 {

    /**
     *    @see ConfigurableBeanFactory#SCOPE_PROTOTYPE
     * 	 * @see ConfigurableBeanFactory#SCOPE_SINGLETON
     * 	 * @see org.springframework.web.context.WebApplicationContext#SCOPE_REQUEST
     * 	 * @see org.springframework.web.context.WebApplicationContext#SCOPE_SESSION
     *
     * 	 String SCOPE_SINGLETON = "singleton";
     * 	     单实例的(默认值):ioc容器启动的时【即AnnotationConfigApplicationContext创建完成时】
     * 	                    会调用方法创建对象放到ioc容器中。以后每次获取就是直接从容器中（map.get())中拿
     *
     * 	 String SCOPE_PROTOTYPE = "prototype";
     * 	     多实例的：ioc容器启动并不会调用方法创建对象，放到容器中。
     * 	             每次调用的时候【context.getBean()】才会调用方法创建对象
     *
     * 	 SCOPE_REQUEST="request"：同一个请求创建一个实例
     * 	 SCOPE_SESSION = "session"; 同一个session创建一个实例
     *
     * 	 懒加载：
     * 	   单实例Bean默认在容器启动的时候创建对象
     * 	   懒加载：容器启动不创建对象。第一次使用（获取）Bean创建对象，并初始化；
     * 	          之后每次获取就不会重复创建。
     *     如何使用懒加载：使用@Lazy(true).该注解的value值默认是true，可以不设置
     */
    //@Scope("prototype")
    //@Lazy(true)
    @Lazy
    @Bean("person")
    public Person person(){
        System.out.println("对象创建完成。。。。");
        return  new Person("jack",25);
    }

    /**
     *
     * @Conditional({Conditional}),按照一定的条件进行判断，满足条件给容器中注册bean 【重点】
     * 如果是windows系统，给容器注册("bill")
     * 如果是linux系统，给容器注册("linux")
     *
     */
    @Conditional({WindowsCondition.class})
    @Bean("bill")
    public Person person01(){
        return new Person("Bill Gates",62);
    }

    @Conditional(LinuxCondition.class)
    @Bean("linux")
    public Person person02(){

        return new Person("linux",65);
    }

    /**
     * 给容器中注册组件
     * 1、包扫描+组件标注注解{@Controller/@Service/@Repository/@Component}【自己定义的类】
     * 2、@Bean【导入第三方包里面的组件】
     * 3、@Import【快速给容器中导入一个组件】【重点】
     *    （1）第一种用法:@Import(要导致到容器注解的类型):容器就会自动注册这个组件，id默认就是全类名
     *    （2）第二种用法：@Import(ImportSelector选择器类型)：返回需要导入的组件的全类名数组
     *     (3) @Import(ImportBeanDefinitionRegistrar类型)：手动注册bean
     *     【注意】这三种方式可以联合使用
     * 4、使用Spring提供的bean调用getObject创建的对象
     *    （1）默认获取到的是工厂bean的getObject方法创建的对象
     *    （2）要想获取到工厂bean本身，我们需要给id前面加一个$符号
     *        如：
     *        Object bean1 = context.getBean("&colorFactoryBean");
     */
    @Bean
    public ColorFactoryBean colorFactoryBean(){
        return new ColorFactoryBean();
    }


    /**
     * 测试bean的生命周期:
     *    bean创建 ---初始化 ---销毁的过程
     * 容器管理bean的生命周期
     * 我们可以自定义初始化和销毁方法，容器在bean进行到当前生命周期的时候来调用我们自定义的初始化和销毁方法
     *
     * 1、指定初始化和销毁方法的方式：
     *   （1）通过@Bean的方式指定init-method 和 destroy-method方法
     *       如：@Bean(initMethod = "init",destroyMethod = "destroy")
     *        【注意】初始化方法和销毁的方法不能有参数，并且要在对应的bean中
     *        单实例：在容器启动的时候创建对象。
     *        多实例：在每次获取【不同的对象】的时候创建对象
     *   （2）实现InitializingBean接口【定义初始化逻辑】和DisposableBean【定义销毁逻辑】
     *    (3)可以使用JSR250的方式：
     *            @PostConstruct：在bean创建完成并且属性赋值完成时，来执行初始化的方法
     *            @PreDestroy：在容器销毁bean之前通知我们进行清除工作
     * 2、初始化：
     *    对象创建完成，并赋值好的时候，调用初始化方法
     * 3、销毁方法：
     *    单实例：容器关闭的时候，执行销毁方法
     *    多实例：容器不会管理这个bean，容器不会执行销毁方法
     *
     * 4、BeanPostProcessor【interface】，bean的后置处理器
     *   在bean初始化前后进行一些处理工作
     *   postProcessBeforeInitialization：在初始化方法执行之前执行
     *   postProcessAfterInitialization：在初始化方法执行之后执行
     *
     * Spring底层对BeanPostProcessor的使用
     *   bean赋值，注入其他组件，@Autowired，生命周期注解功能，@Async,都有xxxBeanPostProcessor
     * @return
     */
    //@Scope("prototype")
    /*@Bean(initMethod = "init",destroyMethod = "destroy")
    public Car car(){
        Car car = new Car();
        car.setName("aabb");
        return car;
    }*/

    /**
     * bean的初始化方法和销毁方法的第二种实现。
     * 测试这里的时候，帮上面的注销掉。因为容器会注入所有的bean，以及执行他们的初始化方法，看起来比较乱
     */
    /*@Bean
    public Book book(){
        Book book = new Book();
        book.setName("时间简史");
        return book;
    }*/

    /**
     * bean的初始化方法和销毁方法的第三种实现。
     */
    @Bean
    public Dog dog(){
        Dog dog = new Dog();
        dog.setName("二哈");
        return dog;
    }
    //将后置处理器放到容器中
    @Bean
    public MyBeanPostProcessor myBeanPostProcessor(){
        return new MyBeanPostProcessor();
    }
}
