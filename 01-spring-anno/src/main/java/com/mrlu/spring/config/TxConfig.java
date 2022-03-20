package com.mrlu.spring.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

/**
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-03-07 15:14
 *
 * 声明式事务：
 * 环境搭建
 * 1、导入相关依赖
 *      数据源、数据库启动、Spring-jdbc模块
 * 2、配置数据源，JdbcTemplate(Spring提供的简化数据库操作工具)操作数据
 * 3、给方法上标注@Transactional表示当前方法是一个事务方法
 * 4、@EnableTransactionManagement开启基于注解的事务管理功能
 *     @EnableXXX
 * 5、配置事务管理器来控制事务
 *
 * 原理：
 *  1、@EnableTransactionManagement
 *      利用TransactionManagementConfigurationSelector给容器中导入组件：
 *         1、如果是JDK的动态代理：
 *           导入：AutoProxyRegistrar和ProxyTransactionManagementConfiguration组件
 *         2、如果是AspectJ的就导入：
 *           determineTransactionAspectClass组件
 * 2、AutoProxyRegistrar
 *          给容器中注册一个InfrastructureAdvisorAutoProxyCreator组件：
 *          InfrastructureAdvisorAutoProxyCreator组件是一个后置处理器。
 *          利用后置处理器机制在对象创建以后，包装对象，返回一个代理对象(增强器)，代理对象执行方法利用拦截器链进行拦截
 * 3、ProxyTransactionManagementConfiguration做了什么？
 *         1、给容器中注册事务增强器（该bean的名字：internalTransactionAdvisor）
 *            （1）事务增强器要用事务注解的信息：AnnotationTransactionAttributeSource解析事务
 *            （2）事务拦截器：
 *                 TransactionInterceptor：保存了事务属性信息，事务管理器
 *                 他是一个MethodInterceptor类型
 *                 在目标方法执行的时候：
 *                 执行拦截器链：
 *                    TransactionInterceptor的invoke方法
 *  *                 invoke方法===调用===》invokeWithinTransaction
 *                 事务拦截器：
 *                    invokeWithinTransaction方法：
 *                    1、先获取事务相关的属性
 *                    2、再获取PlatformTransactionManager，如果事先没有添加TransactionManager
 *                       最终会从容器中按照类型获取一个TransactionManager
 *                    3、执行目标方法
 *                       （1）如果异常，获取到事务管理器，利用事务回滚操作
 *                       （2）如果正常，利用事务管理器，提交事务
 *
 *
 *          invokeWithinTransaction方法的一部分
 *          if (txAttr == null || !(ptm instanceof CallbackPreferringPlatformTransactionManager)) {
 * 			// Standard transaction demarcation with getTransaction and commit/rollback calls.
 * 			TransactionInfo txInfo = createTransactionIfNecessary(ptm, txAttr, joinpointIdentification);
 *
 * 			Object retVal;
 * 			try {
 * 				// This is an around advice: Invoke the next interceptor in the chain.
 * 				// This will normally result in a target object being invoked.
 * 				retVal = invocation.proceedWithInvocation();
 *                        }
 * 			catch (Throwable ex) {
 * 				// target invocation exception
 * 				completeTransactionAfterThrowing(txInfo, ex);  【获取事务管理器，回滚事务】
 * 				throw ex;【抛出异常】
 *            }
 * 			finally {
 * 				cleanupTransactionInfo(txInfo);
 *            }
 *
 * 			if (vavrPresent && VavrDelegate.isVavrTry(retVal)) {
 * 				// Set rollback-only in case of Vavr failure matching our rollback rules...
 * 				TransactionStatus status = txInfo.getTransactionStatus();
 * 				if (status != null && txAttr != null) {
 * 					retVal = VavrDelegate.evaluateTryFailure(retVal, txAttr, status);
 *                }
 *            }
 *
 * 			commitTransactionAfterReturning(txInfo);【提交事务】
 * 			return retVal;
 * 			}
 */
@EnableTransactionManagement
@Configuration
@ComponentScan(value = {"com.mrlu.spring.dao","com.mrlu.spring.service"})
public class TxConfig {

    @Bean
    public DataSource dataSource() throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass("com.mysql.jdbc.Driver");
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/springtest");
        dataSource.setUser("root");
        dataSource.setPassword("root");
        return dataSource;
    }
    @Bean
    public JdbcTemplate jdbcTemplate() throws PropertyVetoException {
        //Spring对@Configuration类会特殊处理，给容器中加组件的方法，多次调用都只是从容器中找组件
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource());
        return jdbcTemplate;
    }

    //注册事务管理器在容器中
    @Bean
    public PlatformTransactionManager transactionManager() throws PropertyVetoException {
        return new DataSourceTransactionManager(dataSource());
    }
}
