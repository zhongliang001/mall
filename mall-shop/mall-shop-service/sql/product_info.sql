create table product_info (
	prd_id varchar(32) not null primary key comment '产品id',
	prd_name varchar(64) comment '商品名',
	prd_type varchar(2) comment '商品类型',
    platform_prd_id varchar(32) comment '平台商品id',
	vendor_id varchar(32) comment '供应商id',
	shop_id varchar(32) comment '商铺id',
    create_date varchar(10) comment '创建日期',
    created_user_id varchar(32) comment '创建人id',
	update_date varchar(10) comment '修改时间',	
	update_user_id varchar(32) comment '修改人id',
	state char(1) comment '上架状态',
	grd_date varchar(10) comment '上架日期',
	version INT NULL DEFAULT 0 COMMENT '版本号' 
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COMMENT='商品信息';