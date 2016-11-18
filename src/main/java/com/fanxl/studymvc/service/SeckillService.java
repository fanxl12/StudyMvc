package com.fanxl.studymvc.service;

import com.fanxl.studymvc.dto.Exposer;
import com.fanxl.studymvc.dto.SeckillExecution;
import com.fanxl.studymvc.entity.Seckill;
import com.fanxl.studymvc.exception.RepeatKillException;
import com.fanxl.studymvc.exception.SeckillCloseException;
import com.fanxl.studymvc.exception.SeckillException;

import java.util.List;

/**
 * Created by fanxl2 on 2016/11/18.
 */
public interface SeckillService {

    List<Seckill> getSeckillList();

    Seckill getById(long seckillId);

    Exposer exportSeckillUrl(long seckillId);

    SeckillExecution executeSeckill(long seckillId, long userPhone, String md5)
            throws SeckillException, RepeatKillException, SeckillCloseException;



}
