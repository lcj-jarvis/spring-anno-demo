package com.mrlu.springmvc.contoller;

import org.springframework.web.context.request.async.DeferredResult;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-03-10 15:57
 */
public class DeferredResultQueue {
    private static Queue<DeferredResult<Object>> queue = new ConcurrentLinkedQueue<>();

    public static void save(DeferredResult<Object> deferredResult){
        queue.add(deferredResult);
    }

    public static  DeferredResult<Object> get(){
        //返回先进来的
        return queue.poll();
    }
}
