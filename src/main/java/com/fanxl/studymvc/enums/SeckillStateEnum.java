package com.fanxl.studymvc.enums;

import com.fanxl.studymvc.entity.Seckill;

/**
 * 使用枚举表示常亮
 * Created by fanxl2 on 2016/11/18.
 */
public enum SeckillStateEnum {
    SUCCESS(1, "秒杀成功"),
    END(2, "秒杀结束"),
    REPEAT_KILL(-1, "重复秒杀"),
    INNER_ERROR(-2, "系统异常"),
    DATA_REWRITE(-3, "数据篡改");

    private int state;

    private String stateInfo;

    SeckillStateEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public static SeckillStateEnum stateOf(int index){
        for (SeckillStateEnum state : values()){
            if (state.getState()==index){
                return state;
            }
        }
        return null;
    }
}
