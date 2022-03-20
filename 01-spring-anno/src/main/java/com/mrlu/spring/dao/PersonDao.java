package com.mrlu.spring.dao;

import org.springframework.stereotype.Repository;

/**
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-03-04 17:18
 */
@Repository
public class PersonDao {
    private String label = "1";

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return "PersonDao{" +
                "label='" + label + '\'' +
                '}';
    }
}
