package com.mrlu.spring.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

/**
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-03-06 15:43
 *
 * 切面类
 *  @Aspect告诉spring当前类是切面类
 */

@Aspect
public class LogAspect {

    /**
     *   抽取公共的切入点表达式
     *   1、本类引用
     *   2、其他的切面引用
     */
    @Pointcut("execution(public Integer com.mrlu.spring.aop.MathCalculator.*(..))")
    public void pointCut(){}

    //JoinPoint只能是第一个参数
    @Before("pointCut()")
    public void logStart(JoinPoint joinPoint){
        System.out.println("除法"+joinPoint.getSignature().getName()+"运行之前@Before....参数列表是："+ Arrays.toString(joinPoint.getArgs()));
    }

    @After("pointCut()")
    public void logEnd(JoinPoint joinPoint){
        System.out.println("除法"+joinPoint.getSignature().getName()+"结束");
    }

    @AfterReturning(value = "pointCut()",returning = "result")
    public void logReturn(JoinPoint joinPoint,Object result){
        System.out.println("除法"+joinPoint.getSignature().getName()+"正常返回。。。运行的结果是："+result);
    }

    @AfterThrowing(value = "pointCut()",throwing = "exception")
    public void logException(JoinPoint joinPoint,Exception exception){
        System.out.println("除法"+joinPoint.getSignature().getName()+"异常。。。异常信息是："+exception);
    }


}
