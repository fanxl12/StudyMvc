-- 数据库初始化脚本

-- 创建数据库
CREATE DATABASE seckill;
-- 使用数据库
use seckill;

-- 创建秒杀库存表
CREATE TABLE seckill(
    `seckill_id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '商品库存Id',
    `name` VARCHAR(120) NOT NULL COMMENT '商品名称',
    `number` INT NOT NULL COMMENT '库存数量',
    `start_time` TIMESTAMP NOT NULL COMMENT '秒杀开启时间',
    `end_time` TIMESTAMP NOT NULL COMMENT '秒杀结束时间',
    `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (seckill_id),
    KEY idx_start_time (start_time),
    KEY idx_end_time (end_time),
    KEY idx_create_time (create_time)
)ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='秒杀库存表';

-- 初始化数据
INSERT into seckill (name, number, start_time, end_time)
VALUES
 ('1000元秒杀iPhone6', 100, '2016-11-16 00:00:00', '2016-11-17 00:00:00'),
 ('500元秒杀红米4', 200, '2016-11-16 00:00:00', '2016-11-17 00:00:00'),
 ('10元秒杀小米平板', 100, '2016-11-16 00:00:00', '2016-11-17 00:00:00'),
 ('200元秒杀魅族5', 600, '2016-11-16 00:00:00', '2016-11-17 00:00:00'),
 ('300元秒杀乐视电视', 400, '2016-11-16 00:00:00', '2016-11-17 00:00:00');

-- 秒杀成功明细表
-- 用户登录认证相关信息
CREATE TABLE success_killed(
  `seckill_id` bigint not null comment '秒杀商品Id',
  `user_phone` bigint not null comment '用户手机号',
  `state` TINYINT NOT NULL DEFAULT -1 COMMENT '状态指示：-1 无效 0：成功 1：已付款',
  `create_time` TIMESTAMP NOT NULL COMMENT '创建时间',
  PRIMARY KEY (seckill_id, user_phone), /*联合主键*/
  KEY idx_create_time(create_time)
)ENGINE =InnoDB DEFAULT CHARSET=utf8 COMMENT = '秒杀成功明细表';

