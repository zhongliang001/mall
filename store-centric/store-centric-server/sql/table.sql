use storeCentric;

create table store (
 	store_id varchar(32) not null primary key comment '店铺id',
 	store_name varchar(200) comment '店铺名称',
 	store_type varchar(2) comment '店铺类型：1-个人店铺；2-企业店铺',
 	cert_type varchar(2) comment '证件类型',
 	cert_code varchar(20) comment '证件号码',
 	main_busi varchar(5) comment '店铺主营业务',
 	store_desc varchar(200) comment '店铺描述',
 	store_logo_id varchar(32) comment '店铺logo', 
 	create_time varchar(20) comment '创建时间',
 	create_user_id varchar(32) comment '创建人id',
 	update_time varchar(20) comment '修改时间',
 	update_user_id varchar(32) comment '修改人id',
 	store_status varchar(1) comment '店铺状态；1-正常，2-注销，3-封禁',
 	province varchar(5) comment '省',
 	city varchar(5) comment '市',
 	area varchar(5) comment '区', 	
 	store_addr varchar(200) comment '店铺地址'
)comment '店铺表' ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4;


create table store_manager(
	mannger_id varchar(32) not null primary key comment '店铺管理员id',
	store_id varchar(32) not null comment '店铺id',
	user_id varchar(32) comment '用户id',
	create_time varchar(20) comment '创建时间',
 	create_user_id varchar(32) comment '创建人id',
 	update_time varchar(20) comment '修改时间',
 	update_user_id varchar(32) comment '修改人id'，
 	invitation_code varchar(6) comment '邀请码',
 	invitation_code_status varchar(1) comment '邀请码状态；1-待激活，2-激活；3.失效'，
 	invitation_code_times int comment '激活码使用次数'
)comment '店铺管理员表' ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4;

create table mananger_role(
	mannger_role_id varchar(32) not null primary key comment '店铺管理员id',
	mannger_id varchar(32) comment '店铺管理员id',
	role_id varchar(32) comment '角色id',
	create_time varchar(20) comment '创建时间',
 	create_user_id varchar(32) comment '创建人id',
 	update_time varchar(20) comment '修改时间',
 	update_user_id varchar(32) comment '修改人id'，
 	mananger_role_status varchar(1) comment '管理员角色状态1-生效, 失效'
)comment '店铺管理员角色表' ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4;


