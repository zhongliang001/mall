create table history_info (
	id varchar(32) not null primary key comment '主键',
	table_name varchar(64) comment '表名',
	pri_key varchar(32) comment '主键',
	pri_value  varchar(20) comment '主键值',
	mod_key  varchar(32) comment '修改的字段',
	mod_name  varchar(32) comment '修改的字段',
	old_value varchar(100) comment '原数据',
	new_value varchar(100) comment '新数据',
	update_time varchar(20) comment '修改时间',
	update_user_id varchar(32) comment '修改人id',
	version int comment '版本号'
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COMMENT='历史记录表';
