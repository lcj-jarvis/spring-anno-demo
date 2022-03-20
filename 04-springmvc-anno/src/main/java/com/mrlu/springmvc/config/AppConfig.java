package com.mrlu.springmvc.config;

import com.mrlu.springmvc.Interceptor.MyInterceptor;
import com.mrlu.springmvc.Interceptor.TimeoutCallableProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.scheduling.concurrent.ConcurrentTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-03-09 22:12
 *
 * SpringMVC的容器只扫描@Controller注解
 */
@ComponentScan(value = "com.mrlu.springmvc",useDefaultFilters = false,includeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION,value = Controller.class)
})
//相当于<mvc:annotation-driven/>
//第一种方式：继承WebMvcConfigurerAdapter，第二种方式实现WebMvcConfigurer接口
@EnableWebMvc
public class AppConfig implements WebMvcConfigurer {
    //可以根据需要选择重写方法配置

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
         registry.viewResolver(new InternalResourceViewResolver("/WEB-INF/views/",".jsp"));
    }

    //相当于<mvc:default-servlet-handler/>
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    //添加拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyInterceptor());
    }


    /**
     * 配置线程池。用于异步请求
     * @return
     */
    @Bean(name = "asyncPoolTaskExecutor")
    public ThreadPoolTaskExecutor getAsyncThreadPoolTaskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(20);
        taskExecutor.setMaxPoolSize(200);
        taskExecutor.setQueueCapacity(25);
        taskExecutor.setKeepAliveSeconds(200);
        taskExecutor.setThreadNamePrefix("MrLu-");
        // 线程池对拒绝任务（无线程可用）的处理策略，目前只支持AbortPolicy、CallerRunsPolicy；默认为后者
        taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        taskExecutor.initialize();
        return taskExecutor;
    }

    /**
     * 配置支持异步请求
     * @param configurer
     */
    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
        //处理 callable超时
        //configurer.setDefaultTimeout(3000);
        //配置异步请求超时后的拦截器
        configurer.registerCallableInterceptors(new TimeoutCallableProcessor());
        configurer.setTaskExecutor(getAsyncThreadPoolTaskExecutor());

        //【第二种方式】
        //ConcurrentTaskExecutor taskExecutor = new ConcurrentTaskExecutor();
        //【设置超时时间】
        //configurer.setDefaultTimeout(3000);
        //configurer.setTaskExecutor(taskExecutor);
    }

}

