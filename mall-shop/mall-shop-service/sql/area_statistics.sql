DROP TABLE IF EXISTS shopCenter.area_statistics;
create table shopCenter.area_statistics(
    id varchar(32) not null primary key comment '统计编号',
    statistics_date varchar(10) not null comment '统计日期',
    area varchar(6) comment '省份',
    visitors int comment '访客数',
    orders int comment '订单数'
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COMMENT='地域统计数';