drop database if exists servlet_blog;
create database servlet_blog character set utf8mb4;
use servlet_blog;

drop table if exists user;
create table user(
userId int primary key auto_increment,
username varchar(20) not null unique,
password varchar(20) not null,
nickName varchar(20),
sex varchar(3),
birthday date,
head varchar(50)
);

insert into user(username,password) values ('a',1);
insert into user(username,password) values ('b',2);
insert into user(username,password) values ('c',2);

drop table if exists article;
create table article(
 id int primary key auto_increment,
 title varchar(20) not null,
 content mediumtext not null,
 create_time timestamp default now(),
 view_count int default 0,
 userId int,
 foreign key(userId) references user(userId)
);

insert into article( title, content, userId) values ('快速排序','public ***',1);
insert into article( title, content, userId) values ('希尔排序','public ***',2);
insert into article( title, content, userId) values ('选择排序','public ***',3);
insert into article( title, content, userId) values ('归并排序','public ***',1);

select userId, username, password, nickName, sex, birthday, head from user where username='a';
