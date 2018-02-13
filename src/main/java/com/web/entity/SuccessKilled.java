package com.web.entity;

import java.util.Date;

public class SuccessKilled {
    private long seckill_id;
    private long Userphone;
    private int state;
    private Date createTime;

    //多对一，映射关系，秒杀多个商品对应的那个商品类别
    private SecKill seckill;

    public long getSeckill_id() {
        return seckill_id;
    }

    public void setSeckill_id(long seckill_id) {
        this.seckill_id = seckill_id;
    }

    public long getUserphone() {
        return Userphone;
    }

    public void setUserphone(long userphone) {
        Userphone = userphone;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "SuccessKilled{" +
                "seckill_id=" + seckill_id +
                ", Userphone=" + Userphone +
                ", state=" + state +
                ", createTime=" + createTime +
                '}';
    }
}