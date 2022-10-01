use userCentric;

create table user(
	user_id varchar(32) not null primary key comment '用户id',
	user_name varchar(64) comment '用户名',
	password varchar(64) comment '密码',
	last_login_time varchar(20) comment '最后登录时间',
	login_error_time varchar(20) comment '最后登录错误时间',
	login_error_times int comment '登录错误次数',
	modify_time varchar(20) comment '密码最后修改时间',
	user_status char(1) comment '用户状态：1-正常,2-锁定,3-失效',
	is_mananger char(1) comment '是否管理员：0-否；1-是'
)comment '用户表' ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4;

create table role_conf(
	role_id varchar(32) not null primary key comment '角色id',
	role_name varchar(64) comment '角色名称',
	create_time varchar(20) comment '创建时间',
 	create_user_id varchar(32) comment '创建人id',
 	update_time varchar(20) comment '修改时间',
 	update_user_id varchar(32) comment '修改人id',
 	role_role_status varchar(1) comment '角色状态1-生效, 失效',
 	is_inner_role char(1) comment '是否内部角色：0-否；1-是'
)comment '角色配置表' ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4;

create table role_right_conf(
	role_right_id varchar(32) not null primary key comment '角色权限d',
	role_id varchar(32) comment '角色id',
	right_id  varchar(32) comment '权限id',
	create_time varchar(20) comment '创建时间',
 	create_user_id varchar(32) comment '创建人id',
 	update_time varchar(20) comment '修改时间',
 	update_user_id varchar(32) comment '修改人id',
 	role_role_status varchar(1) comment '权限状态1-生效, 失效'
)comment '角色权限配置表' ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4;

create table right_conf(
	right_id  varchar(32) comment '权限id',
	right_name  varchar(64) comment '权限名称',
	create_time varchar(20) comment '创建时间',
 	create_user_id varchar(32) comment '创建人id',
 	update_time varchar(20) comment '修改时间',
 	update_user_id varchar(32) comment '修改人id',
 	role_role_status varchar(1) comment '权限状态1-生效, 失效'
)comment '权限配置表' ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4;

create table right_action_conf(
	right_action_id  varchar(32) not null primary key comment '权限操作id',
	right_id  varchar(32) comment '权限id',
	action_id  varchar(64) comment '操作id',
	create_time varchar(20) comment '创建时间',
 	create_user_id varchar(32) comment '创建人id',
 	update_time varchar(20) comment '修改时间',
 	update_user_id varchar(32) comment '修改人id',
 	role_role_status varchar(1) comment '权限状态1-生效, 失效'
)comment '权限配置表' ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4;

 create table menu(
    menu_id        varchar(32) not null primary key comment '菜单id',
    menu_name      varchar(64) comment '菜单名',
    path      varchar(256) comment '菜单路由',
    parent_id varchar(32) comment '父菜单id',
    file_path varchar(32) comment '组件文件'
) ENGINE = InnoDB comment '菜单表'  DEFAULT CHARSET=utf8mb4;
  
  create table action(
    acton_id      varchar(10) not null primary key comment '操作id',
    name    varchar(20) comment '操作名称',
    oper    varchar(20) comment '操作',
    menu_id varchar(32) comment '菜单id',
    menu_name varchar(64) comment '菜单名',
    path    varchar(30) comment '操作路径'
) ENGINE = InnoDB  DEFAULT CHARSET = utf8mb4 comment '操作表';

