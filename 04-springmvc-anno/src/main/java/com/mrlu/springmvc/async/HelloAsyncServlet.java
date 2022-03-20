package com.mrlu.springmvc.async;

import javax.servlet.AsyncContext;
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
 * @createDate 2021-03-10 0:41
 *
 * 在Servlet 3.0之前，Servlet采用Thread-Per-Request的方式处理请求。
 * 即每一次Http请求都由某一个线程从头到尾负责处理。
 *
 * 如果一个请求需要进行IO操作，比如访问数据库、调用第三方服务接口等，那么其所对应的线程将同步地等待IO操作完成，
 * 而IO操作是非常慢的，所以此时的线程并不能及时地释放回线程池以供后续使用，在并发量越来越大的情况下，
 * 这将带来严重的性能问题。
 * 即便是像Spring、Struts这样的高层框架也脱离不了这样的桎梏，因为他们都是建立在Servlet之上的。
 * 为了解决这样的问题，Servlet 3.0引入了异步处理，
 * 然后在Servlet 3.1中又引入了非阻塞IO来进一步增强异步处理的性能。
 *
 * Servlet3.0的异步处理:asyncSupported = true 表示支持异步处理
 */
@WebServlet(value = "/async",asyncSupported = true)
public class HelloAsyncServlet  extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long begin = System.currentTimeMillis();
        System.out.println("主线程 "+Thread.currentThread()+" start.....");
        //1、asyncSupported = true 表示开启异步处理
        //2、开启异步模式
        AsyncContext asyncContext = req.startAsync();
        //设置超时时间
        //asyncContext.setTimeout(5000);
        //3、处理业务逻辑，开始异步处理
        asyncContext.start(new Runnable() {
            @Override
            public void run() {
                try {
                    long FBegin = System.currentTimeMillis();
                    System.out.println("副线程 "+Thread.currentThread()+" start.....");
                    sayHello();
                    //获取到异步上下文
                    asyncContext.complete();
                    asyncContext.getResponse().getWriter().println("hello async...");
                    System.out.println("副线程 "+Thread.currentThread()+" end.....");
                    System.out.println("副线程花费：" + (System.currentTimeMillis()- FBegin) + "ms");

                } catch (InterruptedException | IOException e) {
                    e.printStackTrace();
                }
            }
        });
        System.out.println("主线程 "+Thread.currentThread()+" end.....");
        System.out.println("主线程花费：" + (System.currentTimeMillis()-begin) + "ms");
    }

    public void sayHello() throws InterruptedException {
        System.out.println(Thread.currentThread()+"processing.....");
        Thread.sleep(5000);
    }
}
