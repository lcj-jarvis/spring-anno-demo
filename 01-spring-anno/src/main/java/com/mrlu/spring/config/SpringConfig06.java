package com.mrlu.spring.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mrlu.spring.bean.Blue;
import com.mrlu.spring.bean.Red;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.util.StringValueResolver;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

/**
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-03-06 14:36
 * Profile：Spring为我们提供的可以根据当前环境，动态激活和切换一系列组件的功能
 *         开发环境     测试环境      生成环境
 * 数据源：   （/A）      (/B)         (/C)
 *
 * @Profile:指定组件在哪个环境的情况下才能被注册到容器中，不指定，任何环境下都能注册这个组件
 *    （1）加了环境标识的bean，只有在这个环境被激活的时候才能注册到容器中，默认是default环境
 *     (2)写在配置类上，只有指定环境的的时候，找那个配置类里面的所有配置才能开始生效
 *     (3)没有标注环境标识的bean，任何环境下都是加载的
 */
//@Profile("test")
@PropertySource("classpath:jdbc.properties")
@Configuration
public class SpringConfig06 implements EmbeddedValueResolverAware {

    @Value("${jdbc.username}")
    private String user;

    private StringValueResolver resolver;
    private String driverClass;


    @Profile("test")
    @Bean
    public Blue blue(){
        return  new Blue();
    }

    @Bean
    public Red red(){
        return new Red();
    }

    //环境标识，自定义的，随便写
    //@Profile("default")
    @Profile("test")
    @Bean("testDataSource")
    public DataSource dataSourceTest(@Value("${jdbc.password")String password) throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass(driverClass);
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/springtest");
        dataSource.setUser(user);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Profile("dev")
    @Bean("devDataSource")
    public DataSource dataSourceDev(@Value("${jdbc.password")String password) throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass(driverClass);
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/ssm");
        dataSource.setUser(user);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Profile("prod")
    @Bean("prodDataSource")
    public DataSource dataSourceProd(@Value("${jdbc.password")String password) throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass(driverClass);
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/ssm_crud");
        dataSource.setUser(user);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        this.resolver = resolver;
        driverClass = resolver.resolveStringValue("${jdbc.driverClassName}");
    }
}
