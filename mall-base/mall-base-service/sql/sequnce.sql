create table sequnce(
	seq_id  varchar(20) not null primary key comment '序列id',
    name varchar(20) comment '序列名',
    seq_no INT UNSIGNED NOT NULL comment '序列编号'
)ENGINE = InnoDB
 DEFAULT CHARSET = utf8mb4 comment '序列表';
