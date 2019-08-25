# 华北理工社区
专注于师生的服务

项目创建人：卿人

时间2019.8.22

sql语句
user 表
```sql
CREATE TABLE IF NOT EXISTS `user`(
   `id` INT UNSIGNED AUTO_INCREMENT,
   `accountId` VARCHAR(100) NOT NULL,
   `name` VARCHAR(40) NOT NULL,
   `token` VARCHAR(40) NOT NULL,
   `gmtCreate` bigint NOT NULL,
   `gmtModify;` bigint NOT NULL,
   PRIMARY KEY ( `id` )
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
alter table user add column bio varchar(256);

````
topic表
```sql
create table if not exists `topic`(
   `id` INT UNSIGNED AUTO_INCREMENT,
   `title` VARCHAR(100) NOT NULL,
   `description` text,
   `createId` int NOT NULL,
   `gmtCreate` bigint NOT NULL,
   `gmtModify` bigint NOT NULL,
   `comment_count` int default 0,
   `view_count` int default 0,
   `like_count` int default 0,
   `tag` VARCHAR(100) NOT NULL,
   PRIMARY KEY ( `id` )
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
```