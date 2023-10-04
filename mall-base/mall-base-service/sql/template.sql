create table template
(
    id              varchar(32) not null primary key comment '主键id',
    name            varchar(32) comment '模板名',
    temp            varchar(64) comment '模板',
    length          varchar(20) comment '长度'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 comment '流水模板表';