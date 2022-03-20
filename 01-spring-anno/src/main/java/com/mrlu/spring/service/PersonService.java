package com.mrlu.spring.service;

import com.mrlu.spring.dao.PersonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.inject.Inject;

/**
 * @author Mr.Lu
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-03-04 17:19
 */
@Service
public class PersonService {
    /*@Autowired
    private PersonDao personDao;

    @Override
    public String toString() {
        return "PersonService{" +
                "personDao=" + personDao +
                '}';
    }*/

    /*@Autowired
    private PersonDao personDao2;

    @Override
    public String toString() {
        return "PersonService{" +
                "personDao=" + personDao2 +
                '}';
    }*/

    /*@Autowired
    @Qualifier("personDao3")
    private PersonDao personDao;

    @Override
    public String toString() {
        return "PersonService{" +
                "personDao=" + personDao +
                '}';
    }*/

    /*@Autowired(required = false)
    private PersonDao personDao4;

    @Override
    public String toString() {
        return "PersonService{" +
                "personDao=" + personDao4 +
                '}';
    }*/

   /* @Autowired
    private PersonDao personDao;

    @Override
    public String toString() {
        return "PersonService{" +
                "personDao=" + personDao +
                '}';
    }
*/

    /*@Resource
    private PersonDao personDao;

    @Override
    public String toString() {
        return "PersonService{" +
                "personDao=" + personDao +
                '}';
    }*/

    /*@Resource(name = "personDao2")
    private PersonDao personDao;

    @Override
    public String toString() {
        return "PersonService{" +
                "personDao=" + personDao +
                '}';
    }*/

    @Inject
    private PersonDao personDao2;

    @Override
    public String toString() {
        return "PersonService{" +
                "personDao=" + personDao2 +
                '}';
    }
}
