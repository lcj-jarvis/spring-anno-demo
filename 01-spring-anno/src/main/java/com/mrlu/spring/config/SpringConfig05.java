package com.mrlu.spring.config;

import com.mrlu.spring.bean.Boss;
import com.mrlu.spring.bean.Employee;
import com.mrlu.spring.dao.PersonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-03-06 12:36
 */
@Configuration
@ComponentScan(value = {"com.mrlu.spring.controller","com.mrlu.spring.dao","com.mrlu.spring.service","com.mrlu.spring.bean"})
public class SpringConfig05 {

    /**
     * 1、@Autowired:自动注入
     *    （1）默认优先按照类型区容器中找相应的组件：context.getBean(PersonDao.class);
     *     (2)如果找到多个相同的类型，再将属性的名称作为组件的id去容器中查找
     *        context.getBean("personDao");
     *     (3)@Qualifier("personDao3"):使用@Qualifier指定要装配的组件的id，而不是使用属性名
     *     (4)自动装配默认一定要将属性赋值好，没有就会报错
     *        @Autowired(required = false)表示不是必须装配的
     *        【测试这个的时候，要将所有的bean都不注入】
     *     (5) @Primary,使用该注解，让Spring进行自动装配的时候，默认使用首选的bean
     *            也可以继续使用@Qualifier指定需要装配的bean的名字
     *2、Spring还支持使用@Resource【JSR250的】和@inject【JSR330的】[都是java规范注解]
     *     @Resource：可以和@Autowired一样实现自动装配的功能，默认是按照组件的名称进行自动装配的
     *                没有能支持@Primary功能，没有支持@Autowired(required = false)的功能
     *     @inject:需要导入javax.inject的依赖，和Autowired的功能是一样的，支持@Primary功能，
     *               没有required=false的功能
     *
     *     @Autowired是Spring定义的，@Resource和 @inject都是java的规范
     *
     *     AutowiredAnnotationBeanPostProcessor：解析完成自动装配功能
     */
    @Bean("personDao2")
    public PersonDao personDao(){
       PersonDao personDao = new PersonDao();
       personDao.setLabel("2");
       return personDao;
    }

    @Bean
    public PersonDao personDao3(){
        PersonDao personDao = new PersonDao();
        personDao.setLabel("3");
        return personDao;
    }

    @Bean
    @Primary
    public PersonDao personDao5(){
        PersonDao personDao = new PersonDao();
        personDao.setLabel("5");
        return personDao;
    }

    /**
     * @Autowired:构造器，参数，方法，属性；都是从容器中获取参数组件的值
     * （1）标注在方法的位置上：@Bean + 方法参数：参数从容器中获取的，默认不写@Autowired注解
     *  (2)标注在构造器上：如果组件中只有一个有参构造器，这个有参构造器的@Autowired可以省略不写
     * （3）标注在参数的位置
     */
    //@Autowired
    /*@Bean
    public Boss boss1(@Autowired Employee employee){
        Boss boss = new Boss(employee);
        return boss;
    }*/

    @Bean
    public Boss boss1(Employee employee){
        Boss boss = new Boss(employee);
        return boss;
    }
}
