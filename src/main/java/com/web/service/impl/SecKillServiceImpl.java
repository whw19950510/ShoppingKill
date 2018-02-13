package com.web.service.impl;

import com.web.dao.SeckillDAO;
import com.web.dao.SuccessKilledDAO;
import com.web.dto.Exposer;
import com.web.dto.SecKillExecution;
import com.web.entity.SecKill;
import com.web.entity.SuccessKilled;
import com.web.enums.SecKillStateEnum;
import com.web.exception.RepeatKillException;
import com.web.exception.SecKillCloseException;
import com.web.exception.SeckillException;
import com.web.service.SecKillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

@Service
public class SecKillServiceImpl implements SecKillService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    //Spring容器中获取实例，注入相应容器里
    @Autowired
    private SeckillDAO seckillDAO;
    @Autowired
    private SuccessKilledDAO successKilledDAO;
    public final String slat = "dasdsajfwenewqeuqiwo";//混淆MD5

    public List<SecKill> getSeckillList() {
        List<SecKill> ans = seckillDAO.queryAll(0, 4);
        return ans;
    }

    public SecKill getById(long seckillId) {
        SecKill ans = seckillDAO.queryById(seckillId);
        return ans;
    }

    /*
    判断是否秒杀成功，才返回接口地址，否则返回秒杀的开启和结束时间
     */
    @Transactional
    public Exposer exportSeckillUrl(long seckillId) {
        SecKill seckill = seckillDAO.queryById(seckillId);
        if (seckill == null) return new Exposer(false, seckillId);
        Date startTime = seckill.getStartTime();
        Date endTime = seckill.getEndTime();
        //系统当前时间
        Date now = new Date();
        if (now.getTime() < startTime.getTime() || now.getTime() > endTime.getTime()) {
            return new Exposer(false, seckillId, now.getTime(), startTime.getTime(), endTime.getTime());
        }
        String md5 = getMD5(seckillId);
        // 混淆字符串
        return new Exposer(true, md5, seckillId);
    }

    //产生md5的方法，单独抽象分离
    private String getMD5(long seckillId) {
        String base = seckillId + "/" + slat;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }

    //执行秒杀
    public SecKillExecution executeSeckill(long seckillId, long userPhone, String md5) throws SeckillException, SecKillCloseException, RepeatKillException {
        if (md5 == null || !md5.equals(getMD5(seckillId))) {
            throw new SeckillException("data rewrite");
        }
        //执行秒杀逻辑，减库存+记录购买行为
        Date nowTime = new Date();
        try {
            int updateCount = seckillDAO.reduceNumber(seckillId, nowTime);
            if (updateCount <= 0) {
                throw new SecKillCloseException("The service is close, end the flow");
            } else {
                //记录购买行为
                int insertCount = successKilledDAO.insertSuccessKilled(seckillId, userPhone);
                //如果插入冲突就会返回0，忽略(insert ignore)
                if (insertCount <= 0) {
                    throw new RepeatKillException("repeat operation");
                } else {
                    SuccessKilled successKilled = successKilledDAO.queryByIdWithSeckill(seckillId, userPhone);
                    return new SecKillExecution(seckillId, SecKillStateEnum.SUCCESS, successKilled);
                }
            }
        } catch (SecKillCloseException e1) {
            throw e1;
        } catch (RepeatKillException e2) {
            throw e2;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new SeckillException("seckill inner error" + e.getMessage());
        }


    }
}
