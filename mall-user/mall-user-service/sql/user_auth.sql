create table user_auth (
	user_id varchar(32) not null primary key comment '用户id',
	user_name varchar(64) comment '用户名',
	email varchar(64) comment '邮箱',
	password varchar(32) comment '密码',
	login_time varchar(20) comment '登录时间',
	regist_time varchar(20) comment '注册时间',
	login_error_time varchar(20) comment '登录失败时间',
	login_error_times int comment '登录失败次数',
	user_state char(1) comment '用户状态'
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COMMENT='用户授权信息表';
