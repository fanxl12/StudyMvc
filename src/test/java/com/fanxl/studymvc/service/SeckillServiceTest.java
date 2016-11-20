package com.fanxl.studymvc.service;

import com.fanxl.studymvc.dto.Exposer;
import com.fanxl.studymvc.dto.SeckillExecution;
import com.fanxl.studymvc.entity.Seckill;
import com.fanxl.studymvc.exception.RepeatKillException;
import com.fanxl.studymvc.exception.SeckillCloseException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by fanxl on 2016/11/20 0020.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml",
                       "classpath:spring/spring-service.xml"})
public class SeckillServiceTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private SeckillService seckillService;

    @Test
    public void getSeckillList() throws Exception {
        List<Seckill> list = seckillService.getSeckillList();
        logger.info("list={}", list);
    }

    @Test
    public void getById() throws Exception {
        long id = 1000;
        Seckill seckill = seckillService.getById(id);
        logger.info("seckill={}", seckill);
    }



    @Test
    public void exportSeckill() throws Exception {
        long id = 1001;
        Exposer exposer = seckillService.exportSeckillUrl(id);
        if (exposer.isExposed()){
            logger.info("exposer={}", exposer);
            long userPhone = 13120752266L;
            String md5 = "fadd652b5e105b816145afd12772d98b";
            try {
                SeckillExecution execution = seckillService.executeSeckill(id, userPhone, md5);
                logger.info("result={}", execution);
            }catch (RepeatKillException e){
                logger.error(e.getMessage());
            }catch (SeckillCloseException e){
                logger.error(e.getMessage());
            }
        }else {
            logger.warn("exposer={}", exposer);
        }

        //fadd652b5e105b816145afd12772d98b
    }
}