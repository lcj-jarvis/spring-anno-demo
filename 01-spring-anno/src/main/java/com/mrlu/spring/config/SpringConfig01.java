package com.mrlu.spring.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mrlu.spring.bean.Person;
import com.mrlu.spring.controller.PersonController;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-03-04 17:02
 *
 * 配置类==配置文件
 *
 * @ComponentScan value指定要扫描的包
 * 指定扫描的时候按照声明规则排除哪些组件
 *    excludeFilters = Filter[] : type：指定扫描的时候排除的方式，classes：表示要排除的类型
 * 指定扫描的时候只需要包含声明组件
 *    includeFilters = Filter[] : type：指定扫描的时候采用的方式，classes：表示要扫描的类型
 *    【注意】如果要使用includeFilters，就要配置useDefaultFilters为false
 *
 * FilterType.ASSIGNABLE_TYPE : 按照指定的类型
 * @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,classes = PersonController.class)
 * 不管是PersonController或者它的子类都会过滤。
 *
 *  FilterType.ANNOTATION:按照注解类型
 *  FilterType.ASPECTJ：使用ASPECTJ
 *  FilterType.REGEX:使用正则表达式指定
 *  FilterType.CUSTOM：使用自定义规则。要编写一个类实现TypeFilter接口
 *
 *  @Configuration的value属性表示配置类的id值，
 *    proxyBeanMethods默认是true表示从容器中获取组件。
 *   不管是在配置类内部还是外部直接调用person01等bean的方法。返回的都是单实例。
 *   而且获取到的配置类是CGLIB增强后的。
 *
 *   如果proxyBeanMethods设置为false。获取的配置类就是没有增强过的。
 *   也不是在容器中获取组件，返回的都是重新创建的实例（多实例）。
 *
 *   useDefaultFilters=true 是否使用默认规则，默认规则扫描@Component，@Service，@Controller，@Repository注解
 */
//告诉Spring这是一个配置类
@Configuration(value = "mySpringConfig",proxyBeanMethods = false)
//@ComponentScans注解可以包含多个@ComponentScan注解
/*@ComponentScans({
        @ComponentScan(value = {"com.mrlu.spring"},excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ANNOTATION,classes = {Controller.class})},
                includeFilters = { @ComponentScan.Filter(type = FilterType.ANNOTATION,classes = {Service.class, Repository.class})},
                useDefaultFilters = false
        )
})*/
@ComponentScan(value = {"com.mrlu.spring"},
        excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION,classes = {Controller.class}),
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,classes = PersonController.class),

        @ComponentScan.Filter(type = FilterType.CUSTOM,
                classes = {MyTypeFilter.class})

        },

        includeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION,classes = {Service.class, Repository.class}),
        //@ComponentScan.Filter(type = FilterType.CUSTOM,classes = {MyTypeFilter.class})
        },
        useDefaultFilters = false
)
public class SpringConfig01 {

    /**
     * 给spring容器注册一个Bean，类型为返回值类型。
     * 如果没有设置@Bean注解的value值，id默认是用方法名作为id
     * @return
     */
    @Bean(value = "person")
    public Person person01(){
        return new Person("jack",20);
    }

    @Bean
    public JdbcTemplate jdbcTemplate(){
        return new JdbcTemplate(new ComboPooledDataSource());
    }
}
