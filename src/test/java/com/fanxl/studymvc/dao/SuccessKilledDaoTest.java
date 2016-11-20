package com.fanxl.studymvc.dao;

import com.fanxl.studymvc.entity.SuccessKilled;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Created by fanxl on 2016/11/17 0017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SuccessKilledDaoTest {

    @Resource
    private SuccessKilledDao successKilledDao;

    @Test
    public void insertSuccessKilled() throws Exception {
        long id = 1002;
        long userPhone = 13120752269L;
        int result = successKilledDao.insertSuccessKilled(id, userPhone);
        System.out.println(result);
    }

    @Test
    public void queryByIdWithSeckill() throws Exception {
        long id = 1001;
        long userPhone = 13120752269L;
        SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(id, userPhone);
        System.out.println(successKilled);
        System.out.println(successKilled.getSeckill());
    }

}