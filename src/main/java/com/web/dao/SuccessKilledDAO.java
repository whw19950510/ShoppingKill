package com.web.dao;
import com.web.entity.SuccessKilled;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SuccessKilledDAO {
    // 插入购买明细，可过滤重复
    //使用联合主键过滤重复
    // return 插入的行数
    public int insertSuccessKilled(@Param("seckillId") long successkilledId, @Param("userPhone")long userPhone);
    //查询秒杀并携带秒杀产品对象
    SuccessKilled queryByIdWithSeckill(@Param("seckillId") long successkilledId, @Param("userPhone")long userPhone);

    List<SuccessKilled> queryAll();
}