package com.mrlu.spring.config;

import org.springframework.core.io.Resource;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

import java.io.IOException;

/**
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-03-04 17:54
 *
 * 自定义过滤规则
 */
public class MyTypeFilter implements TypeFilter {

    /**
     * @param metadataReader 读取到当前正在扫描的类的信息
     * @param metadataReaderFactory 可以获取到任何类的信息
     * @return whether this filter matches true表示符合过滤规则，false表示不符合
     * @throws IOException
     */
    @Override
    public boolean match(MetadataReader metadataReader,
                         MetadataReaderFactory metadataReaderFactory) throws IOException {
        //获取当前类的注解信息
        AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();
        //获取当前正在扫描的类的信息
        ClassMetadata classMetadata = metadataReader.getClassMetadata();
        //获取当前类资源（类的路径）
        Resource resource = metadataReader.getResource();

        String className = classMetadata.getClassName();
        System.out.println("--->"+className);
        //扫描类名中包含Person的
        if (className.contains("Person")){
            System.out.println("=======包名包含Person=======");
            return true;
           // return false;
        }
        return false;
        //return true;
    }
}
