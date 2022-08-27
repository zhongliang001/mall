use userCentric;

create table user(
	user_id varchar(32) not null primary key comment '用户id',
	user_name varchar(64) comment '用户名',
	password varchar(64) comment '密码',
	last_login_time varchar(20) comment '最后登录时间',
	login_error_time varchar(20) comment '最后登录错误时间',
	login_error_times int comment '登录错误次数',
	modify_time varchar(20) comment '密码最后修改时间',
	user_status char(1) comment '用户状态：1-正常，2-锁定,3-失效'
)comment '用户表' ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4;