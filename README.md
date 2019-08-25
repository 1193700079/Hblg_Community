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
添加了lombok插件

导入项目的童鞋记得下载此插件


# 目前项目存在的问题
1.图片显示
```html

<img class="media-object"  src="https://avatars1.githubusercontent.com/u/43908729?v=4">
```

2.span标签被thymeleaf替代
```html

<span th:text="${topicDTO.commentCount}"> 个回复 </span>
```
