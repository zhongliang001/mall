create table vendor (
    vendor_id varchar(32) not null primary key comment '供应商id',
	vendor_name varchar(64) comment '供应商名',
	conventional char(1) comment '是否实体店',
	path varchar(128) comment '供应商网址',
	shop_id varchar(32) comment '商铺id',
	cooper_status char(1) comment '合作状态:0-未合作，1-合作',
	created_date varchar(10) comment '创建时间',	
	created_user_id varchar(32) comment '创建人id',
	update_date varchar(10) comment '修改时间',	
	update_user_id varchar(32) comment '修改人id'
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COMMENT='商铺信息';
