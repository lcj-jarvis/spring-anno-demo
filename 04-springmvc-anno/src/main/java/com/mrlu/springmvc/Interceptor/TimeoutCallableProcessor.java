package com.mrlu.springmvc.Interceptor;

import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.async.CallableProcessingInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.Callable;

/**
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-03-10 13:46
 */
public class TimeoutCallableProcessor implements CallableProcessingInterceptor {

    @Override
    public <T> Object handleTimeout(NativeWebRequest request, Callable<T> task) throws Exception {
        System.out.println("超时。。。。。");
        HttpServletRequest httpRequest = request.getNativeRequest(HttpServletRequest.class);
        HttpServletResponse httpResponse = request.getNativeResponse(HttpServletResponse.class);
        //记录超时的url地址
        CustomAsyncRequestTimeoutException timeoutException = new CustomAsyncRequestTimeoutException(httpRequest.getRequestURI());

        httpRequest.setAttribute("timeoutException",timeoutException);
        httpRequest.getRequestDispatcher("WEB-INF/views/timeout.jsp").forward(httpRequest, httpResponse);

        /*if (!httpResponse.isCommitted()) {
            httpResponse.setContentType("text/plain;charset=utf-8");
            httpResponse.getWriter().write("超时了.....");
            httpResponse.getWriter().close();
        }*/
        return timeoutException;
    }

    /**
     * 处理发生的错误
     * @param request
     * @param task
     * @param t
     * @param <T>
     * @return
     * @throws Exception
     */
    /*@Override
    public <T> Object handleError(NativeWebRequest request, Callable<T> task, Throwable t) throws Exception {
        return null;
    }*/
}
