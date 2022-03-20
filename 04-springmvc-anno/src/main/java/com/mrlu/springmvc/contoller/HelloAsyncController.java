package com.mrlu.springmvc.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.context.request.async.WebAsyncTask;

import java.util.UUID;
import java.util.concurrent.Callable;

/**
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-03-10 12:03
 */
@Controller
public class HelloAsyncController {

    /**
     * SpringMVC处理异步请求的第一种方式的原理
     * 1、控制器返回Callable
     * 2、Spring异步处理，将Callable提交到TaskExecutor使用一个隔离的线程进行执行
     * 3、DispatcherServlet和所有的Filter退出web容器的线程，但是response保存打开的状态
     * 4、Callable返回结果，SpringMVC将请求重新派发给容器，恢复之前的处理
     * 5、根据Callable返回的结果，SpringMVC继续进行视图渲染流程等（发起请求--》视图渲染流程等）
     *
     * @return
     * MyInterceptor...preHandle
     * 主线程开始：Thread[http-nio-8080-exec-31,5,main]===>1615361897247
     * 主线程结束：Thread[http-nio-8080-exec-31,5,main]===>1615361897247
     * 副线程开始：Thread[MrLu-1,5,main]===>1615361897266
     * 副线程结束：Thread[MrLu-1,5,main]===>1615361902267
     * ===========================Callable执行完成===============================
     * MyInterceptor...preHandle   重新发起请求/04_springmvc_anno/helloAsync01
     * MyInterceptor...postHandle   （Callable执行完成的返回值就是目标方法（helloAsync方法）的返回值）
     * MyInterceptor...afterCompletion
     *
     */
    @RequestMapping("/helloAsync01")
    @ResponseBody
    public Callable<String> helloAsync(){

        System.out.println("主线程开始："+Thread.currentThread()+"===>"+System.currentTimeMillis());
        //Callable的泛型也可以使用ModelAndView
        Callable<String> callable = () -> {
            System.out.println("副线程开始："+Thread.currentThread()+"===>"+System.currentTimeMillis());
            Thread.sleep(5000);
            System.out.println("副线程结束："+Thread.currentThread()+"===>"+System.currentTimeMillis());
            return "Hello SpringMVC Async";
        };
        System.out.println("主线程结束："+Thread.currentThread()+"===>"+System.currentTimeMillis());
        return callable;
    }

    /**
     * 处理异步请求的第二种方式
     * @return
     */
    @RequestMapping("/helloAsync02")
    @ResponseBody
    public WebAsyncTask<String> helloAsync02(){
        System.out.println("主线程开始："+Thread.currentThread()+"===>"+System.currentTimeMillis());
        Callable<String> callable = () -> {
            System.out.println("副线程开始："+Thread.currentThread()+"===>"+System.currentTimeMillis());
            Thread.sleep(5000);
            System.out.println("副线程结束："+Thread.currentThread()+"===>"+System.currentTimeMillis());
            return "Hello SpringMVC Async";
        };
        System.out.println("主线程结束："+Thread.currentThread()+"===>"+System.currentTimeMillis());
        // 这里设置Callable的超时时间为3s，超时则抛出超时异常，通过自定义的拦截器来处理该超时异常
        // 对于Callable子线程的超时的处理，在AppConfig中配置(这里：采取的处理是，抛出自定义的超时异常)
        // 即：如果在3s内，Callable子线程未完成，则抛出自定义的超时异常（也可以在配置类中设置超时时间）
        return new WebAsyncTask<>(3000,callable);
    }

    @Autowired
    private ThreadPoolTaskExecutor poolTaskExecutor;
    /**
     * @return
     * MyInterceptor...preHandle
     * 主线程开始：Thread[http-nio-8080-exec-20,5,main]===>1615364738186
     * 主线程结束：Thread[http-nio-8080-exec-20,5,main]===>1615364738193
     * 副线程开始：Thread[MrLu-1,5,main]===>1615364738194
     * 创建的订单：9ccdf293-7eaa-4ea7-a091-78d58625cba6
     * 副线程结束：Thread[MrLu-1,5,main]===>1615364741195
     * MyInterceptor...preHandle
     * MyInterceptor...postHandle
     * MyInterceptor...afterCompletion
     */
    @RequestMapping("/createOrder")
    @ResponseBody
    public DeferredResult<Object> createOrder(){
        //第一个参数为超时的时间，第二个参数为超时的信息
        System.out.println("主线程开始："+Thread.currentThread()+"===>"+System.currentTimeMillis());
        //这里的泛型当然也可以写ModelAndView
        DeferredResult<Object> deferredResult = new DeferredResult<>((long) 5000, "create fail...");
        //保存要创建的订单到队列中
        DeferredResultQueue.save(deferredResult);

        //创建订单的线程
        Runnable runnable = () -> {
            System.out.println("副线程开始："+Thread.currentThread()+"===>"+System.currentTimeMillis());
            try {
                create();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("副线程结束："+Thread.currentThread()+"===>"+System.currentTimeMillis());
        };
        poolTaskExecutor.execute(runnable);

        System.out.println("主线程结束："+Thread.currentThread()+"===>"+System.currentTimeMillis());
        return deferredResult;
    }

    //模拟创建订单的耗时操作。
    public void create() throws InterruptedException {
        String order = UUID.randomUUID().toString();
        DeferredResult<Object> objectDeferredResult = DeferredResultQueue.get();
        //设置DeferredResult的值并进行处理。调用setResult(order);会将order返回给createOrder()方法
        //相当于该结果作为上面方法的返回结果
        Thread.sleep(3000);
        objectDeferredResult.setResult(order);
        System.out.println("创建的订单："+order);
    }
}
