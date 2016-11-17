package com.fanxl.studymvc.dao;

import com.fanxl.studymvc.entity.SuccessKilled;
import org.apache.ibatis.annotations.Param;

/**
 * Created by fanxl2 on 2016/11/16.
 */
public interface SuccessKilledDao {

    int insertSuccessKilled(@Param("seckillId") long seckillId, @Param("userPhone") long userPhone);

    SuccessKilled queryByIdWithSeckill(long seckillId);

}
