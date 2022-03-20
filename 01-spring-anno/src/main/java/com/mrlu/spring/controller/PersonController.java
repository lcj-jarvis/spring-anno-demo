package com.mrlu.spring.controller;

import com.mrlu.spring.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-03-04 17:19
 */
@Controller
public class PersonController {
    @Autowired
    private PersonService personService;
}
