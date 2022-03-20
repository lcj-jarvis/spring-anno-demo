package com.mrlu.spring.config;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-03-04 21:42
 */
public class MyImportSelector implements ImportSelector {
    /**
     *
     * @param importingClassMetadata 当前标注的@Import注解的类的其他信息以及所有的注释信息
     * @return
     */
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        //不可以返回null
        return new String[]{"com.mrlu.spring.bean.Yellow","com.mrlu.spring.bean.Gray"};
    }
}
