package com.mrlu.spring.service;

import com.mrlu.spring.dao.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-03-07 15:31
 */
@Service
public class StudentService {
    @Autowired
    private StudentDao studentDao;

    @Transactional
    public int insertStudent(){
        int insert = studentDao.insert();
        int a = 10 / 0;
        System.out.println("插入完成");
        return insert;
    }
}
