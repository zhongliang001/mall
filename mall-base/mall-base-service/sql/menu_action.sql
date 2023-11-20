create table menu_action (
	action_id varchar(32) not null primary key comment '操作id',
	menu_id varchar(32) comment '菜单id',
	action_code varchar(64) comment '操作编码',
	action_name varchar(64) comment '操作名称',
	state varchar(1) comment '操作状态：0-未生效；2-生效'
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COMMENT='菜单操作表';
