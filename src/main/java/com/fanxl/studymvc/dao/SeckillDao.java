package com.fanxl.studymvc.dao;

import com.fanxl.studymvc.entity.Seckill;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by fanxl on 2016/11/15 0015.
 */
public interface SeckillDao {

    int reduceNumber(@Param("seckillId") long seckillId, @Param("killTime") Date killTime);

    Seckill queryById(long seckillId);

    List<Seckill> queryAll(@Param("offset") int offset, @Param("limit") int limit);

}
