package com.web.dto;

import com.web.entity.SuccessKilled;
import com.web.enums.SecKillStateEnum;

public class SecKillExecution {
    private long seckillId;
    private int state;//秒杀成功、失败的状态
    private String stateInfo;//
    private SuccessKilled successKilled;

    public SecKillExecution(long seckillId, SecKillStateEnum secKillStateEnum, SuccessKilled successKilled) {
        this.seckillId = seckillId;
        this.state = secKillStateEnum.getState();
        this.stateInfo = secKillStateEnum.getStateInfo();
        this.successKilled = successKilled;
    }
    //失败秒杀的构造
    public SecKillExecution(long seckillId, SecKillStateEnum secKillStateEnum) {
        this.seckillId = seckillId;
        this.state = secKillStateEnum.getState();
        this.stateInfo = secKillStateEnum.getStateInfo();
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
}
