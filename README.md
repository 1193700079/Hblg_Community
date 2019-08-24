# 华北理工社区
专注于师生的服务

项目创建人：卿人

时间2019.8.22

sql语句
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
````