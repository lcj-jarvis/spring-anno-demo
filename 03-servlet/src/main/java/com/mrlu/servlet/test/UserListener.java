package com.mrlu.servlet.test;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-03-09 20:44
 */
public class UserListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("UserListener。。。contextInitialized。。。");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("UserListener。。。contextDestroyed。。。");
    }
}
