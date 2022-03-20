package com.mrlu.spring.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-03-06 13:38
 */
//默认加在ioc容器中的组件，容器启动会调用无参构造器创建对象，在进行初始化赋值等操作
@Component
public class Boss {
    private Employee employee;

    /**
     *
     * 构造器要用的组件，都是从容器中获取的
     * @Autowired用在有参构造器的时候，放在方法形参的效果和放在构造器位置的效果是引用的
     * 如果这个组件只有一个有参构造器，，这个有参构造的@Autowired可以省略，
     * 参数位置的组件还是可以自动从容器中获取的
     */
    //@Autowired
    /*public Boss(@Autowired Employee employee) {
        this.employee = employee;
        System.out.println("Boss。。。的有参构造器");
    }
*/
    public Boss(Employee employee) {
        this.employee = employee;
        System.out.println("Boss。。。的有参构造器");
    }

    @Override
    public String toString() {
        return "Boss{" +
                "employee=" + employee +
                '}';
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
    /**
     *
     * @Autowired标注在方法上面，Spring容器创建当前对象，就会调用方法，完成赋值
     * 方法使用的参数，自定义类型的值从ioc容器中获取.
     *
     */
    /*@Autowired
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }*/
}
