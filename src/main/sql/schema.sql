--数据库初始化脚本

--创建数据库
CREATE DATABASE seckill;
--使用数据库
use seckill;

--创建秒杀库存表
CREATE TABLE seckill(
    'seckill_id' bigint not null auto_increment comment '商品库存Id',
    'name' VARCHAR(120) NOT NULL comment '商品名称',
    'number' int NOT NULL comment '库存数量',
    'start_time' TIMESTAMP NOT NULL comment '秒杀开启时间',
    'end_time' TIMESTAMP NOT NULL comment '秒杀结束时间',
    'create_time' TIMESTAMP not NULL DEFAULT CURRENT_TIMESTAMP comment '创建时间',
    PRIMARY KEY (seckill_id),
    KEY idx_start_time (start_time),
    KEY idx_end_time (end_time),
    KEY idx_create_time (create_time)
)ENGINE=InnoDB Auto_increment=1000 DEFAULT charset=uft8 comment = '秒杀库存表'

--初始化数据
insert into seckill (name, number, start_time, end_time)
VALUES
 ('1000元秒杀iPhone6', 100, '2016-11-16 00:00:00', '2016-11-17 00:00:00'),
 ('500元秒杀红米4', 200, '2016-11-16 00:00:00', '2016-11-17 00:00:00'),
 ('10元秒杀小米平板', 100, '2016-11-16 00:00:00', '2016-11-17 00:00:00'),
 ('200元秒杀魅族5', 600, '2016-11-16 00:00:00', '2016-11-17 00:00:00'),
 ('300元秒杀乐视电视', 400, '2016-11-16 00:00:00', '2016-11-17 00:00:00');

