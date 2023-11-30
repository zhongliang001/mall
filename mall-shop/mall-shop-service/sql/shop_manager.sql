create table shop_manager (
 	shop_manager_id varchar(32) not null primary key comment '商铺管理者id',
    shop_id varchar(32) comment '商铺id',
    user_id varchar(32) comment '用户id',
    status char(1) comment '管理者状态',
	created_date varchar(10) comment '创建时间',	
	careated_user_id varchar(32) comment '创建人id',
	update_date varchar(10) comment '修改时间',	
	update_user_id varchar(32) comment '修改人id'
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COMMENT='商铺管理者信息';
