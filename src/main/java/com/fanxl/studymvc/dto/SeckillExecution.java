package com.fanxl.studymvc.dto;

import com.fanxl.studymvc.entity.SuccessKilled;
import com.fanxl.studymvc.enums.SeckillStateEnum;

/**
 * Created by fanxl2 on 2016/11/18.
 */
public class SeckillExecution {

    private long seckillId;

    private int state;

    private String stateInfo;

    private SuccessKilled successKilled;

    public SeckillExecution(long seckillId, SeckillStateEnum stateEnum, SuccessKilled successKilled) {
        this.seckillId = seckillId;
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.successKilled = successKilled;
    }

    public SeckillExecution(long seckillId, SeckillStateEnum stateEnum) {
        this.seckillId = seckillId;
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
    }

    public long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public SuccessKilled getSuccessKilled() {
        return successKilled;
    }

    public void setSuccessKilled(SuccessKilled successKilled) {
        this.successKilled = successKilled;
    }

    @Override
    public String toString() {
        return "SeckillExecution{" +
                "seckillId=" + seckillId +
                ", state=" + state +
                ", stateInfo='" + stateInfo + '\'' +
                ", successKilled=" + successKilled +
                '}';
    }
}
