create table dict (
	dict_id varchar(32) not null primary key comment '字典编号',
	cn_name varchar(64) comment '字典中文名',
	en_name varchar(10) comment '字典代码',
	dict_type  varchar(20) comment '字典类型',
	dict_type_name  varchar(100) comment '字典类型名',
	order_id int comment '字典顺序id'
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COMMENT='字典表';
