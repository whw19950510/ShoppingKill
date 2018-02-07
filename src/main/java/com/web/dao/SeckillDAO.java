package com.web.dao;
import com.web.entity.*;

import java.util.Date;
import java.util.List;

public interface SeckillDAO {
    //减库存，seckillId,killtime
    //return 减库存的数量
    int reduceNumber(long seckillId, Date killTime);
    //根据商品id查询秒杀商品
    SecKill queryById(long seckillId);
    //根据偏移量查询秒杀商品列表
    List<SecKill> queryAll(int offset, int limit);
}