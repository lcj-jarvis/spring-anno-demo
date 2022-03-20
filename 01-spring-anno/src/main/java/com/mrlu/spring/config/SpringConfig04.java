package com.mrlu.spring.config;

import com.mrlu.spring.bean.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

/**
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-03-05 23:22
 *
 * 用@PropertySource读取外部配置文件中的key/value保存到运行时的环境变量中
 * 加载完外部的配置文件以后使用${配置文件的key}取出配置文件的值
 * @PropertySources注解可以包含多个@PropertySource注解
 */
@Configuration
@PropertySource(value = {"classpath:User.properties"})
/*@PropertySources({
        @PropertySource(value = {"classpath:User.properties"})
    }
)*/
public class SpringConfig04 {
    @Bean
    public User user(){
        return new User();
    }
}
