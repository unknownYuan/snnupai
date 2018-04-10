drop database if exists `ghd_snnupai`;
create database `ghd_snnupai`;
use `ghd_snnupai`;

drop table if exists `user`;
CREATE TABLE `user` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '用户id',
	`real_name` VARCHAR(32) NOT NULL DEFAULT '\'\'' COMMENT '真实姓名',
	`nick_name` VARCHAR(16) NOT NULL DEFAULT '\'昵称\'' COMMENT '昵称',
	`password` VARCHAR(64) NULL DEFAULT '\'\'' COMMENT '密码',
	`salt` VARCHAR(64) NULL DEFAULT NULL,
	`phone` VARCHAR(64) NOT NULL DEFAULT '\'\'' COMMENT '手机号',
	`description` VARCHAR(64) NOT NULL DEFAULT '\'\'' COMMENT '个人描述',
	`major` VARCHAR(64) NOT NULL DEFAULT '\'\'' COMMENT '专业',
	`email` VARCHAR(64) NULL DEFAULT NULL COMMENT 'email',
	`status` INT(11) NOT NULL DEFAULT '0' COMMENT '是否被封号',
	`sex` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '默认是女，1表示男',
	`register_status` INT(11) UNSIGNED NOT NULL DEFAULT '0' COMMENT '注册状态',
	`acc_points` INT(11) NOT NULL DEFAULT '0' COMMENT '个人积分',
	`vip` INT(11) NOT NULL DEFAULT '0' COMMENT '是否是vip会员',
	`birth_year` INT(11) NOT NULL DEFAULT '1998' COMMENT '出生年份',
	`head_url` VARCHAR(200) NOT NULL DEFAULT '\'https://ss0.baidu.com/-Po3dSag_xI4khGko9WTAnF6hhy/image/h%3D300/sign=dcb5bee7b88f8c54fcd3c32f0a282dee/c9fcc3cec3fdfc03e1a19bb1d83f8794a5c226d9.jpg\'' COMMENT '头像url',
	`entrance_year` INT(11) NOT NULL DEFAULT '2016' COMMENT '入学年份',
	`created_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`updated_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY (`id`),
	UNIQUE INDEX `nick_name_index` (`nick_name`),
	UNIQUE INDEX `phone_index` (`phone`),
	UNIQUE INDEX `email_index` (`email`)
)
	COMMENT='用户表'
	COLLATE='utf8_general_ci'
	ENGINE=InnoDB
	AUTO_INCREMENT=1199
;
# alter table `user` AUTO_INCREMENT= 1000;


drop table if exists `login_ticket`;
create table `login_ticket`(
	`id` bigint auto_increment primary key,
	`user_id` bigint not null,
	`ticket` varchar(45) not null,
	`expired` datetime not null,
	`status` int default 0,
	unique index `ticket_unique` (`ticket` asc)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='ticket表';

CREATE TABLE `trade` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT 'trade_id',
	`owner_id` BIGINT(20) NOT NULL,
	`anonymous` INT(11) NOT NULL DEFAULT '0' COMMENT '0表示匿名,1表示不匿名',
	`content` VARCHAR(256) NOT NULL COMMENT '交易内容',
	`qq` VARCHAR(16) NULL DEFAULT NULL COMMENT 'qq',
	`weixin` VARCHAR(64) NULL DEFAULT NULL COMMENT 'weixin',
	`contacter` VARCHAR(32) NULL DEFAULT NULL COMMENT '联系人',
	`created_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`price` INT(11) NOT NULL,
	`updated_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY (`id`)
)
	COMMENT='跳蚤市场交易表'
	COLLATE='utf8_general_ci'
	ENGINE=InnoDB
;

-- insert into `trade`(owner_id, anonymous, content, qq, weixin, contacter) VALUES (12, 1, '出售二手车', '1593028064', 'weixin520', '郭浩东');


drop table if exists `comment`;
create table `comment`(
	id bigint auto_increment primary key,
	content text not null comment '评论内容',
	user_id bigint not null,
	entity_id bigint not null,
	entity_type int not null,
	`created_date` datetime not null,
	updated_date DATETIME not null,
	`status` int not null default 0,
	INDEX `entity_index` (`entity_id` ASC, `entity_type` ASC)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='评论表';


drop table if exists `love`;
create table `love`(
	id bigint auto_increment primary key,
	user_id bigint not null,
	content text not null,
	anonymous int default 0 not null comment '0表示匿名,1表示不匿名',
	`created_date` datetime not null,
	updated_date DATETIME not null,
	`status` int not null default 0
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='love表';

drop table if exists `post`;
create table `post`(
	id bigint auto_increment primary key,
	user_id bigint not null,
	title varchar(128) not null,
	content text not null,
	`created_date` datetime not null,
	updated_date DATETIME not null,
	`status` int not null default 0
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='post表';


DROP TABLE IF EXISTS `message`;
CREATE TABLE `message`(
	id bigint AUTO_INCREMENT PRIMARY KEY ,
	from_id BIGINT NOT NULL,
	to_id BIGINT NOT NULL ,
	created_date DATETIME NOT NULL ,
	`has_read` int NOT NULL DEFAULT 0,
	conversation_id VARCHAR(128) NOT NULL,
	content TEXT null,
	INDEX `conversation_index` (`conversation_id` ASC),
	INDEX `created_date` (`created_date` ASC)
)ENGINE =InnoDB DEFAULT CHARSET = utf8 COMMENT ='私信表';

DROP TABLE IF EXISTS `person_signature`;
CREATE TABLE `person_signature`(
	id BIGINT AUTO_INCREMENT PRIMARY KEY ,
	content TEXT NOT NULL
)ENGINE =InnoDB DEFAULT CHARSET = utf8 COMMENT ='个人签名';

DROP TABLE if EXISTS `feed`;
CREATE TABLE `feed`(
	id BIGINT AUTO_INCREMENT PRIMARY KEY ,
	user_id BIGINT NOT NULL ,
	event_type int not null,
	event_id BIGINT not null,
	data TINYTEXT
)ENGINE =InnoDB DEFAULT CHARSET = utf8 COMMENT ='动态';


