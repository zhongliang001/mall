create table role_right (
	right_id  varchar(32) not null primary key comment '权限编号',
	role_id varchar(32) comment '角色编号',
	action_id varchar(32) comment '操作id'
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COMMENT='角色权限表';
