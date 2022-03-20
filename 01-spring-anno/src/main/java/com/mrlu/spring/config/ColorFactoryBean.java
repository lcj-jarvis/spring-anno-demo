package com.mrlu.spring.config;

import com.mrlu.spring.bean.Color;
import org.springframework.beans.factory.FactoryBean;

/**
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-03-04 22:21
 */
public class ColorFactoryBean implements FactoryBean<Color> {

    //返回color对象，这个对象会添加到容器中
    @Override
    public Color getObject() throws Exception {
        return new Color();
    }

    //返回bean的类型
    @Override
    public Class<?> getObjectType() {
        return Color.class;
    }

    //true，表示这个bean是单例。false表示这个bean是多例
    @Override
    public boolean isSingleton() {
        return true;
    }
}
