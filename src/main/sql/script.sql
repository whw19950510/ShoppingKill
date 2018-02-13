CREATE DATABASE seckill;

use seckill;

CREATE TABLE seckill(
  `sec_id` bigint NOT NULL AUTO_INCREMENT COMMENT '商品id',
  `name` VARCHAR(120) NOT NULL COMMENT '商品名称',
  `number` int NOT NULL COMMENT '商品数量',
  `start_time` TIMESTAMP NOT NULL COMMENT '秒杀开启时间',
  `end_time` TIMESTAMP NOT NULL COMMENT '秒杀结束时间',
  `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY(sec_id),
  key idx_start_time(start_time),
  key idx_end_time(end_time),
  key idx_create_time(create_time)
)ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='秒杀库存表';
INSERT INTO seckill (name,number,start_time,end_time)
VALUES ('1000元秒杀iphone8',100,'2018-02-04 00:00:00','2018-02-05 00:00:00'),
('2000元秒杀IPAD mini',50,'2018-02-04 00:00:00','2018-02-04 01:00:00'),
('300元秒杀Huawei Mate8',8,'2018-02-04 00:00:00','2018-02-04 03:00:00'),
('500元秒杀荣耀10',20,'2018-02-04 00:00:00','2018-02-04 00:10:00'),
('300元秒杀Vivo10',50,'2018-02-04 00:00:00','2018-02-05 00:00:00');


--用户登录认证相关信息
CREATE TABLE success_seckill(
`seckill_id` bigint NOT NULL COMMENT '秒杀商品id',
`user_phone` bigint NOT NULL COMMENT '用户电话',
`state` tinyint DEFAULT -1 COMMENT '0成功，-1无效，1已付款，2发货，',
`create_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY(seckill_id,user_phone),--联合主键
key idx_create_time(create_time)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='秒杀成功明细表';