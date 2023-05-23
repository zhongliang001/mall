create table user_info (
	user_id varchar(32) not null primary key comment '用户id',
	user_name varchar(64) comment '用户名',
	nick_name varchar(64) comment '昵称',
	cert_type varchar(4) comment '证件类型',
	cert_code varchar(18) comment '证件号码',
	phone varchar(20) comment '手机号',
	sex char(1) comment '性别'	
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COMMENT='用户信息';
