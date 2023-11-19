create table sequnce(
	seq_id  varchar(20) not null primary key comment '序列id',
    name varchar(20) comment '序列名',
    seq_no INT UNSIGNED NOT NULL comment '序列编号',
    min_no	INT UNSIGNED NOT NULL comment '最小编号', 
    max_no	INT UNSIGNED NOT NULL comment '最大编号', 
    len		INT UNSIGNED NOT NULL comment '长度', 
    step  INT UNSIGNED default 1 comment '步长'
)ENGINE = InnoDB
 DEFAULT CHARSET = utf8mb4 comment '序列表';
