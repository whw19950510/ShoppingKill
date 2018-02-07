package com.web.service;

import com.web.dto.Exposer;
import com.web.dto.SecKillExecution;
import com.web.entity.SecKill;
import com.web.exception.RepeatKillException;
import com.web.exception.SecKillCloseException;
import com.web.exception.SeckillException;

import java.util.List;
/*
站在使用者的角度定义业务接口
 */
public interface SecKillService {
    List<SecKill>getSeckillList();

    SecKill getById(long seckillId);

    /*
    输出秒杀接口地址，秒杀开启时进行输出，否则输出系统时间和秒杀时间
     */
    Exposer exportSeckillUrl(long seckillId);

    /*
    执行秒杀的操作
    需要验证Exposer传递的MD5，与内部的MD5作比较，改变了就拒绝请求
     */
    SecKillExecution executeSeckill(long seckillId, long userPhone, String md5) throws SeckillException,SecKillCloseException,RepeatKillException;
}
