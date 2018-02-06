package com.web.seu.dao;
import com.web.seu.*;
public interface SeckillDAO {
    //减库存，seckillId,killtime
    //return 减库存的数量
    int reduceNumber(long seckillId, Date killTime);
    //根据商品id查询秒杀商品
    Seckill queryById(long seckillId);
    //根据偏移量查询秒杀商品列表
    List<Seckill>queryAll(int offset,int limit);
}