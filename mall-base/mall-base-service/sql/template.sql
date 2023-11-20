create table template
(
    id              varchar(32) not null primary key comment '主键id',
    name            varchar(32) comment '模板名',
    cn_name			varchar(64) comment '模板中文名'
    temp            varchar(64) comment '模板'
   
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 comment '流水模板表';
  
INSERT INTO `` (`id`,`name`,`temp`,`cn_name`) VALUES ('000001','TEMP','${S(TEMP_SEQ,0,999999,1,6)}','模板');
INSERT INTO `` (`id`,`name`,`temp`,`cn_name`) VALUES ('000002','SEQ_TEMP','${S(SEQ,1,999999,1,6)}','流水号模板');