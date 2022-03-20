package com.mrlu.servlet.test;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-03-09 20:43
 */
public class UserFilter implements Filter {
    @Override
    public void destroy() {
        System.out.println("UserFilter。。。destroy");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        System.out.println("UserFilter。。。doFilter");
        chain.doFilter(req, resp);
    }

    @Override
    public void init(FilterConfig config) throws ServletException {
        System.out.println("UserFilter。。。init");
    }
}
