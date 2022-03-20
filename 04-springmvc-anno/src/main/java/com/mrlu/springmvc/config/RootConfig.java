package com.mrlu.springmvc.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;

/**
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-03-09 22:12
 *
 * Spring的容器不扫描，@Controller注解
 */
@ComponentScan(value = "com.mrlu.springmvc",excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION,value = Controller.class)
})
public class RootConfig {

}
