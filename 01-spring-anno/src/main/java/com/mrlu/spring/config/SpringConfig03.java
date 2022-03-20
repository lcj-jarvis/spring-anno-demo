package com.mrlu.spring.config;

import com.mrlu.spring.bean.Blue;
import com.mrlu.spring.bean.Dog;
import com.mrlu.spring.bean.Person;
import com.mrlu.spring.bean.Red;
import com.mrlu.spring.condition.LinuxCondition;
import com.mrlu.spring.condition.WindowsCondition;
import org.springframework.context.annotation.*;


/**
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-03-04 18:08
 *
 *
 * 测试bean的后置处理器
 */

@Configuration
public class SpringConfig03 {
    //将后置处理器放到容器中
    @Bean
    public MyBeanPostProcessor myBeanPostProcessor(){
        return new MyBeanPostProcessor();
    }

    @Bean
    public Dog dog(){
        Dog dog = new Dog();
        dog.setName("二哈");
        return dog;
    }

}
