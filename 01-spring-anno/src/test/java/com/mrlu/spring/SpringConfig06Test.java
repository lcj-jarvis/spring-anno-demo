package com.mrlu.spring;

import com.mrlu.spring.bean.Blue;
import com.mrlu.spring.bean.Person;
import com.mrlu.spring.bean.Red;
import com.mrlu.spring.config.SpringConfig01;
import com.mrlu.spring.config.SpringConfig06;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;

/**
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-03-06 14:46
 */
public class SpringConfig06Test {

    /**
     * 改变环境
     * 1、使用命令行的参数：以test环境为例
     *     Edit Configuration ===> vm-options 加上 -Dspring.profiles.active=test
     */
    @Test
    public void test01(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig06.class);
        String[] namesForType = context.getBeanNamesForType(DataSource.class);
        for (String name:namesForType) {
            //获取bean的id值
            System.out.println(name);
        }
        Blue blue = context.getBean("blue", Blue.class);
        System.out.println(blue);
    }

    /**
     * 改变环境
     * 2、代码的方式
     */
    @Test
    public void test02(){
        //1、创建一个ApplicationContext对象
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        //2、设置需要激活的环境
        //可以激活多个环境
        //context.getEnvironment().setActiveProfiles("test","dev");
        context.getEnvironment().setActiveProfiles("test");
        //3、注册主配置类
        context.register(SpringConfig06.class);
        //4、启动刷新容器
        context.refresh();

        String[] namesForType = context.getBeanNamesForType(DataSource.class);
        for (String name:namesForType) {
            //获取bean的id值
            System.out.println(name);
        }
        Red red = context.getBean("red", Red.class);
        System.out.println(red);
    }
}
