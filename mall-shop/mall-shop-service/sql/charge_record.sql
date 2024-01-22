DROP TABLE IF EXISTS shopCenter.charge_record;
create table shopCenter.charge_record(
    id varchar(32) not null primary key comment '充值编号',
    charge_date varchar(10) not null comment '充值日期',
    charge_amt decimal(6,2) comment '充值金额'
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COMMENT='充值记录';