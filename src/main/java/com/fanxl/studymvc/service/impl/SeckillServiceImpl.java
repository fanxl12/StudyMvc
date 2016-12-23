package com.fanxl.studymvc.service.impl;

import com.fanxl.studymvc.dao.SeckillDao;
import com.fanxl.studymvc.dao.SuccessKilledDao;
import com.fanxl.studymvc.dto.Exposer;
import com.fanxl.studymvc.dto.SeckillExecution;
import com.fanxl.studymvc.entity.Seckill;
import com.fanxl.studymvc.entity.SuccessKilled;
import com.fanxl.studymvc.enums.SeckillStateEnum;
import com.fanxl.studymvc.exception.RepeatKillException;
import com.fanxl.studymvc.exception.SeckillCloseException;
import com.fanxl.studymvc.exception.SeckillException;
import com.fanxl.studymvc.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

/**
 * Created by fanxl2 on 2016/11/18.
 * @Component @Service @Dao @Controller
 */
@Service
public class SeckillServiceImpl implements SeckillService{

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final String slat = "edfgert~@Q#43#%^FGYUH23652&*()I_K<L)PMPI)*&^^&%&G";

    //注入Service依赖  @Resource @Inject
    @Autowired
    private SeckillDao seckillDao;

    @Autowired
    private SuccessKilledDao successKilledDao;

    @Override
    public List<Seckill> getSeckillList() {
        return seckillDao.queryAll(0, 4);
    }

    @Override
    public Seckill getById(long seckillId) {
        return seckillDao.queryById(seckillId);
    }

    @Override
    public Exposer exportSeckillUrl(long seckillId) {
        Seckill seckill = seckillDao.queryById(seckillId);
        if (seckill==null){
            return new Exposer(false, seckillId);
        }
        Date startTime = seckill.getStartTime();
        Date endTime = seckill.getEndTime();
        Date nowTime = new Date();
        if (nowTime.getTime()<startTime.getTime() || nowTime.getTime()>endTime.getTime()){
            return new Exposer(false, seckillId, nowTime.getTime(), startTime.getTime(), endTime.getTime());
        }
        //转化特定字符串的过程，不可逆
        String md5 = getMd5(seckillId);
        return new Exposer(true, md5, seckillId);
    }

    @Override
    @Transactional
    public SeckillExecution executeSeckill(long seckillId, long userPhone, String md5) throws SeckillException, RepeatKillException, SeckillCloseException {

        if (md5==null || !md5.equals(getMd5(seckillId))){
            throw new SeckillException("seckill data rewrite");
        }

        //执行秒杀逻辑： 减库存 + 记录购买行为
        Date nowTime = new Date();
        try {
            int updateCount = seckillDao.reduceNumber(seckillId, nowTime);
            if (updateCount<=0){
                throw new SeckillCloseException("seckill is closed");
            }else {
                int insertCount = successKilledDao.insertSuccessKilled(seckillId, userPhone);
                if (insertCount<=0){
                    throw new RepeatKillException("seckill repeated");
                }else {

                    SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(seckillId, userPhone);
                    return new SeckillExecution(seckillId, SeckillStateEnum.SUCCESS, successKilled);
                }
            }
        }catch (SeckillCloseException e){
            throw e;
        } catch (RepeatKillException e){
            throw e;
        } catch (Exception e){
            logger.error(e.getMessage(), e);
            //所有编译期异常转换为运行时异常
            throw new SeckillException("seckill inner error:"+e.getMessage());
        }
    }

    private String getMd5(long seckillId){
        String base = seckillId + "/" + slat;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }
}
