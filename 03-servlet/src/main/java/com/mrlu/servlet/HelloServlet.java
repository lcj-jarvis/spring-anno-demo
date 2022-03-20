package com.mrlu.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-03-09 18:56
 *
 * @WebServlet 的name属性表示servlet的名字，urlPatterns表示拦截的请求。可以写多个
 */
@WebServlet(name = "HelloServlet",urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().println("你好,servlet3.0!!!");
    }
}
