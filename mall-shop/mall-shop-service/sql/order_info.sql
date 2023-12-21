create table order_info (
	order_id varchar(32) not null primary key comment '订单编号',
	prd_id varchar(32)  comment '产品id',
	sku_id varchar(32) comment '产品sku_id',
	sell_price decimal(16,2) comment '出售价格',
	buyer_id varchar(32) comment '购买者id',
	purchase_time varchar(20) comment '购买时间',
	plat_order_id varchar(32) comment '平台订单号',
	vendor_order_id varchar(32) comment '采购订单号',
	area varchar(6) comment '用户地址',
	state char(1) comment '购买状态',	
    create_date varchar(10) comment '创建日期',
    created_user_id varchar(32) comment '创建人id',
	update_date varchar(10) comment '修改时间',	
	update_user_id varchar(32) comment '修改人id'
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COMMENT='订单信息';

ALTER TABLE `shopCenter`.`order_info` 
ADD COLUMN `purchase_postage` DECIMAL(16,2) NULL COMMENT '采购邮费' AFTER `vendor_order_id`;

ALTER TABLE `shopCenter`.`order_info` 
ADD COLUMN `sell_postage` DECIMAL(16,2) NULL COMMENT '销售邮费' AFTER `vendor_order_id`;

ALTER TABLE `shopCenter`.`order_info` 
ADD COLUMN `sales` INT NULL COMMENT '销售数量' AFTER `vendor_order_id`;
