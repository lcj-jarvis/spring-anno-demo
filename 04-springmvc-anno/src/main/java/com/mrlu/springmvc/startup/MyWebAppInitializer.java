package com.mrlu.springmvc.startup;

import com.mrlu.springmvc.config.AppConfig;
import com.mrlu.springmvc.config.RootConfig;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.FormContentFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

/**
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-03-09 21:57
 *
 * web容器启动的时候创建对象，调用方法来初始化容器和注册中央调度器
 */
public class MyWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    /**
     * 获取根容器的配置类：（相当于Spring的配置文件）父容器
     * @return
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{RootConfig.class};
    }

    /**
     * 获取子容器的配置类：（相当于SpringMVC的配置文件）子容器
     * @return
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{AppConfig.class};
    }

    /**
     * 获取DispatcherServlet的映射信息
     *  "/"：拦截所有的请求,包括静态资源（xx.js,xx.png）,但是不包括*.jsp
     *  "/*":拦截所有的请求，包括静态资源（xx.js,xx.png）,也包括*.jsp.
     *       jsp页面是由jsp引擎解析的。
     * @return
     */
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    /**
     * 添加ssm整合时用到的过滤器
     * @return
     */
    @Override
    protected Filter[] getServletFilters() {

        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter("UTF-8",true,true);
        HiddenHttpMethodFilter hiddenHttpMethodFilter = new HiddenHttpMethodFilter();
        FormContentFilter formContentFilter = new FormContentFilter();
        Filter[] filters = new Filter[]{encodingFilter,hiddenHttpMethodFilter,formContentFilter};
        return filters;
    }

    /**
     * 过滤器拦截的请求
     * @return
     */
    @Override
    protected String getServletName() {
        return "/*";
    }
}
