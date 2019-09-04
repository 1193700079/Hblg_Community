# 华北理工社区
专注于师生的服务

项目创建人：卿人 晓龙 妮妹

时间2019.8.22

sql语句
user 表
```sql
drop table user;
CREATE TABLE IF NOT EXISTS `user`(
   `id` INT UNSIGNED AUTO_INCREMENT,
   `account_id` VARCHAR(100) NOT NULL,
   `name` VARCHAR(40) NOT NULL,
   `token` VARCHAR(36) NOT NULL,
   `gmt_create` bigint NOT NULL,
   `gmt_modify` bigint NOT NULL,
   PRIMARY KEY ( `id` )
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
alter table user add column 	bio varchar(256);
alter table user add column avatar_url varchar(256);

INSERT INTO user (account_id,name,token,gmt_create,gmt_modify,bio,avatar_url)
VALUES ('43908730','卿人','c262ba07-df98-4332-8ae9-acf421a6aaf3','1566718407473','1566718407473','nice','https://avatars1.githubusercontent.com/u/43908729?v=4');

INSERT INTO user (account_id,name,token,gmt_create,gmt_modify,bio,avatar_url)
VALUES ('43908729','卿人','c262ba07-df98-4332-8ae9-acf421a6aaf3','1566718407473','1566718407473','nice','https://avatars1.githubusercontent.com/u/43908729?v=4');

````


topic表
```sql
drop  table topic;
create table if not exists `topic`(
   `id` INT UNSIGNED AUTO_INCREMENT,
   `title` VARCHAR(100) NOT NULL,
   `description` text,
   `create_id` int NOT NULL,
   `gmt_create` bigint NOT NULL,
   `gmt_modify` bigint NOT NULL,
   `comment_count` int default 0,
   `view_count` int default 0,
   `like_count` int default 0,
   `tag` VARCHAR(100) NOT NULL,
   PRIMARY KEY ( `id` )
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into topic (title,description,create_id,gmt_create,gmt_modify,comment_count,view_count,like_count,tag) values('鱼粥','鱼粥真帅 华北理工男神',2,1566718407473,1566718407473,25612,1314,999,'华北理工');

insert into topic (title,description,create_id,gmt_create,gmt_modify,comment_count,view_count,like_count,tag) values('鱼粥','鱼粥真帅 华北理工男神',2,1566718407473,1566718407473,25612,1314,999,'华北理工');


````
comment表
```sql
drop  table comment;
create table if not exists `comment`(
   `id` INT UNSIGNED AUTO_INCREMENT,
   `topic_id` int NOT NULL,
   `commentor_id` int NOT NULL,
   `type` int ,
   `comment_content` text,
   `like_count` int default 0,
   `gmt_create` bigint NOT NULL,
   `gmt_modify` bigint NOT NULL,
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

针对MBG新增插件：
 ```xml
  <!--不生成重复的mapper.xml 这样以后就不用老是去删了-->
 <plugin type="org.mybatis.generator.plugins.UnmergeableXmlMappersPlugin"></plugin>

```
# 项目遇到的问题以及解决方案

遇到pom文件爆红 可以通过找到爆红的依赖 在本地仓库删除 重新  

# 隧道更改
localhost:8080
https://hblgcommunity.free.cngrok.com