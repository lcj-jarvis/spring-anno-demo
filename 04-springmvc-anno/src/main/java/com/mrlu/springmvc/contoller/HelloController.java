package com.mrlu.springmvc.contoller;

import com.mrlu.springmvc.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-03-09 22:18
 */
@Controller
public class HelloController {

    @Autowired
    private HelloService helloService;

    @ResponseBody
    @RequestMapping("/hello")
    public String sayHello(){
        System.out.println("你好。。。");
        return helloService.sayHello("SpringMVC");
    }

    /**
     *
     * @param username
     * @param password
     * @return
     */
    @RequestMapping("/saveUser")
    public ModelAndView saveUser(String username,String password){
        System.out.println(username+"===>"+password);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("username",username);
        modelAndView.addObject("password",password);
        modelAndView.setViewName("success");
        return modelAndView;
    }
}
