# 华北理工社区
专注于师生的服务

项目创建人：卿人 晓龙 妮妹

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
alter table user add column avatar_url varchar(256);

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

# 插件使用
## 添加了lombok插件

导入项目的童鞋记得下载此插件

## 添加了MBG插件 

自动生成单例的model（多表连接还需要手动）

使用的插件 mysql版本需要为5.1.34否则会运行错误

mvn -Dmybatis.generator.overwrite=true mybatis-generator:generate

# 项目遇到的问题以及解决方案

遇到pom文件爆红 可以通过找到爆红的依赖 在本地仓库删除 重新导入