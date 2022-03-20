package com.mrlu.spring;

import com.mrlu.spring.aop.MathCalculator;
import com.mrlu.spring.bean.Person;
import com.mrlu.spring.config.SpringConfig01;
import com.mrlu.spring.config.SpringConfig07;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-03-06 16:25
 *
 */
public class SpringConfig07Test {
    @Test
    public void test01(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig07.class);
        MathCalculator mathCalculator = context.getBean("mathCalculator", MathCalculator.class);
        mathCalculator.div(6, 2);
        //mathCalculator.div(6, 0);
    }
}
