package com.mrlu.servlet;

import com.mrlu.servlet.service.AbstractHelloServletService;
import com.mrlu.servlet.service.AbstractHelloServletServiceExt;
import com.mrlu.servlet.service.HelloServletService;
import com.mrlu.servlet.test.UserFilter;
import com.mrlu.servlet.test.UserListener;
import com.mrlu.servlet.test.UserServlet;

import javax.servlet.*;
import javax.servlet.annotation.HandlesTypes;
import java.util.EnumSet;
import java.util.Set;

/**
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-03-09 19:48
 *
 * 容器启动的时候，会将@HandlesTypes指定的类型下面的子类（实现类，子接口等传递过来）。
 * 保存到onStartup方法形参的Set集合中
 */
@HandlesTypes(value = {AbstractHelloServletService.class, HelloServletService.class})
public class MyServletContainerInitializer implements  ServletContainerInitializer {
    /**
     * 在javax.servlet.ServletContainerInitializer文件里配置本类的全类名。
     * 在应用启动的时候，就会执行onStartup方法
     * (1)使用ServletContext注册Web三大组件
     * (2)使用代码的方式，在项目启动的时候给ServletContext里面添加组件;
     *    必须在项目启动的时候来添加。
     *    （1）ServletContainerInitializer得到的ServletContext
     *    （2）ServletContextListener得到的ServletContext
     * @param c 保存@HandlesTypes指定的类型下面的子类
     * @param servletContext ServletContext保存上下文信息
     * @throws ServletException
     */
    @Override
    public void onStartup(Set<Class<?>> c, ServletContext servletContext) throws ServletException {
        System.out.println("感兴趣的类型：");
        c.forEach(System.out::println);

        //注册三大组件
        ServletRegistration.Dynamic userServlet = servletContext.addServlet("userServlet", new UserServlet());
        //配置Servlet的映射信息
        userServlet.addMapping("/user");

        //注册Listener
        servletContext.addListener(UserListener.class);

        //注册Filter
        FilterRegistration.Dynamic userFilter = servletContext.addFilter("userFilter", UserFilter.class);
        //添加过滤的请求(过滤所有的请求)
        userFilter.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST),true,"/*");
    }
}
