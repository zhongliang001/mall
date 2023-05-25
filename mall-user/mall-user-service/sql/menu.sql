create table menu (
	menu_id varchar(32) not null primary key comment '菜单编号',
	menu_cn_name	varchar(64) comment '菜单中文名',
	menu_name varchar(32) comment '菜单名',
	path varchar(10) comment '菜单路由',
	parent_id  varchar(10) comment '上级菜单编号',
	state varchar(1) comment '菜单状态：0-未上线；2-上线'
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COMMENT='菜单表';
