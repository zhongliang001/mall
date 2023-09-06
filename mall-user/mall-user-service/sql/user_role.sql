create table user_role (
	user_role_id  varchar(32) not null primary key comment '用户角色编号',
	user_id varchar(32) comment '用户id',
	role_id varchar(32) comment '角色编号'
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COMMENT='角色角色表';
