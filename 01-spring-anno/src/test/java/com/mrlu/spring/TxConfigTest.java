package com.mrlu.spring;

import com.mrlu.spring.aop.MathCalculator;
import com.mrlu.spring.config.SpringConfig07;
import com.mrlu.spring.config.TxConfig;
import com.mrlu.spring.service.StudentService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-03-07 15:36
 */
public class TxConfigTest {

    @Test
    public void test01(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TxConfig.class);
        StudentService studentService = context.getBean("studentService", StudentService.class);
        int i = studentService.insertStudent();
        System.out.println(i);
    }
}
