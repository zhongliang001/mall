create table role (
	role_id varchar(32) not null primary key comment '角色编号',
	role_name varchar(64) comment '角色名',
	remark varchar(200) comment '备注',
	state varchar(1) comment '角色状态：0-未上线；2-上线'
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COMMENT='角色表';
