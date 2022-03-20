package com.mrlu.spring.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-03-07 15:31
 */
@Repository
public class StudentDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int insert(){
        String sql = "insert into t_student(name,age) values(?,?)";
        String name = UUID.randomUUID().toString().substring(0,5);
        return jdbcTemplate.update(sql, name, 18);
    }
}
