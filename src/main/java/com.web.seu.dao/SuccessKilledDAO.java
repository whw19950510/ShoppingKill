package com.web.seu.dao;
import com.web.seu.*;
public interface SuccessKilledDAO {
    // 插入购买明细，可过滤重复
    //使用联合主键过滤重复
    // return 插入的行数
    public int insertSuccessKilled(long successkilledId,long userPhone);
    //查询秒杀并携带秒杀产品对象
    SuccessKilled queryByIdWithSeckill(long successkilledId);

    List<SuccessKilled>queryAll();
}