create table sequence(
	seq_id  varchar(20) not null primary key comment '序列id',
    name varchar(20) comment '序列名',
    seq_no INT UNSIGNED NOT NULL comment '序列编号',
    min_no	INT UNSIGNED comment '最小编号', 
    max_no	INT UNSIGNED  comment '最大编号', 
    len		INT UNSIGNED  comment '长度', 
    step  INT UNSIGNED default 1 comment '步长',
    version INT UNSIGNED default 0 comment '版本号'
)ENGINE = InnoDB
 DEFAULT CHARSET = utf8mb4 comment '序列表';

INSERT INTO `` (`seq_id`,`name`,`seq_no`,`min_no`,`max_no`,`len`,`step`,`version`) VALUES ('000001','SEQ',1,1,999999,6,1,2);
INSERT INTO `` (`seq_id`,`name`,`seq_no`,`min_no`,`max_no`,`len`,`step`,`version`) VALUES ('000002','TEMP_SEQ',2,1,999999,6,1,4);
