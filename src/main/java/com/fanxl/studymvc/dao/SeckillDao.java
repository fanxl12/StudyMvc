package com.fanxl.studymvc.dao;

import com.fanxl.studymvc.entity.Seckill;

import java.util.Date;
import java.util.List;

/**
 * Created by fanxl on 2016/11/15 0015.
 */
public interface SeckillDao {

    int reduceNumber(long seckillId, Date killTime);

    Seckill queryById(long seckillId);

    List<Seckill> queryAll(int offset, int limit);

}
