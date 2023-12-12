create table product_sku (
	sku_id varchar(32) not null primary key comment '产品sku_id',
	prd_id varchar(32) not null comment '产品id',
	sku_name varchar(32) comment '产品sku_name',
	sell_price decimal(16,2) comment '出售价格',
	purch_price decimal(16,2) comment '采购价格',
	state char(1) comment '状态',
	version INT NULL DEFAULT 0 COMMENT '版本号' 
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COMMENT='商品sku信息';
