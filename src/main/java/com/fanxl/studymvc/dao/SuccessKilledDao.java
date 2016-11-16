package com.fanxl.studymvc.dao;

import com.fanxl.studymvc.entity.SuccessKilled;

/**
 * Created by fanxl2 on 2016/11/16.
 */
public interface SuccessKilledDao {

    int insertSuccessKilled(long seckillId, long userPhone);

    SuccessKilled queryByIdWithSeckill(long seckillId);

}
