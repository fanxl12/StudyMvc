package com.fanxl.studymvc.dao;

import com.fanxl.studymvc.entity.Seckill;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by fanxl2 on 2016/11/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SeckillDaoTest {

    //注入Dao实现类依赖
    @Resource
    private SeckillDao seckillDao;

    @Test
    public void queryById() throws Exception {
        long id = 1000;
        Seckill seckill = seckillDao.queryById(id);
        System.out.println(seckill.getName());
        System.out.println(seckill);
    }

    @Test
    public void queryAll() throws Exception {
        List<Seckill> seckillList = seckillDao.queryAll(0, 100);
        for (Seckill item : seckillList){
            System.out.println(item);
        }
    }

    @Test
    public void reduceNumber() throws Exception {
        long id = 1000;
        Date date = new Date();
        int result = seckillDao.reduceNumber(id, date);
        System.out.println(result);
    }
}