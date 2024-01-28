create table order_detail (
	order_detail_id varchar(32) not null primary key comment '订单详情编号',
	order_id varchar(32)  comment '订单编号',
	prd_id varchar(32) comment '产品id',
	sku_id varchar(32) comment '产品sku_id',
	sell_price decimal(16,2) comment '出售价格',
	sell_postage DECIMAL(16,2) NULL COMMENT '销售邮费',
	vendor_price DECIMAL(16,2) NULL COMMENT '采购价格',
	purchase_postage DECIMAL(16,2) NULL COMMENT '采购邮费',
	sales INT NULL COMMENT '销售数量' 	
)ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COMMENT='订单详情';

ALTER TABLE `shopCenter`.`order_detail`
ADD COLUMN `shop_id` varchar(32) NULL COMMENT '商铺id' AFTER `sales`;

update  `shopCenter`.`order_detail` set `shop_id` = '20231125000004' where order_id is not null;
