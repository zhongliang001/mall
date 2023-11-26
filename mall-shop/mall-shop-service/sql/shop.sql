create table shop (
    shop_id varchar(32) not null primary key comment '商铺id',
	shop_name varchar(64) comment '商铺名',
	conventional char(1) comment '是否实体店',
	channel varchar(64) comment '铺货渠道',
	path varchar(128) comment '网店地址',
	manage_path varchar(128) comment '店铺管理地址',
	created_date varchar(10) comment '创建时间',	
	created_user_id varchar(32) comment '创建人id',
	update_date varchar(10) comment '修改时间',	
	update_user_id varchar(32) comment '修改人id'
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COMMENT='商铺信息';

