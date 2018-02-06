package com.web.seu.dao;

import com.web.seu.*;

/**
 * 配置Junit和Spring整合
 * Junit加载时启动IOC容器
 * sprint-test,
 */
@Runwith(SpringJunit4ClassRunner.class)
//告诉Junit spring配置文件
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SeckillDAOTest {
    private SeckillDAO seckilldao;
    @Test
    public void TestReduceNumber() throws Exception {

    }
    @Test
    public void TestQueryById throws Exception {
        long id = 1000;
        Seckill seckill = seckilldao.queryById(id);
        System.out.println(seckill.getName());
        System.out.println(seckill);
    }
    @Test
    public void TestQueryAll throws Exception {
        /*
        Java没有保存形参
         */
        List<Seckill> all = seckilldao.queryAll(0,100);
        for(Seckill cur:all) {
            System.out.println(seckill.getName());
            System.out.println(seckill);
        }
    }
}