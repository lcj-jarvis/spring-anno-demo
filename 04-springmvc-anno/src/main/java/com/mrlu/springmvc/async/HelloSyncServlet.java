package com.mrlu.springmvc.async;

import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-03-10 0:56
 */
@WebServlet(value = "/sync")
public class HelloSyncServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long begin = System.currentTimeMillis();
        System.out.println("主线程 "+Thread.currentThread()+" start.....");
        try {
            sayHello();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("主线程 "+Thread.currentThread()+" end.....");
        System.out.println("主线程花费：" + (System.currentTimeMillis()-begin) + "ms");
    }

    public void sayHello() throws InterruptedException {
        System.out.println(Thread.currentThread()+"processing.....");
        Thread.sleep(5000);
    }
}
