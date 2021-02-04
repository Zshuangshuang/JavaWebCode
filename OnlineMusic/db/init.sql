drop database if exists music_server;
create database music_server;
use music_server;

drop table if exists music;
create table music(
    id int primary key auto_increment,
    title varchar(60) not null ,
    singer varchar(30) not null ,
    time varchar(40) not null,
    url varchar(100) not null ,
    userId int
);

drop table if exists user;
create table user(
    id int primary key auto_increment,
    username varchar(30) unique not null ,
    password varchar(50) not null ,
    age int not null ,
    gender varchar(3) not null ,
    email varchar(50) not null
);

drop table if exists lovemusic;
create table lovemusic(
    id int primary key auto_increment,
    user_id int not null ,
    music_id int not null
);

insert into user(username, password, age, gender, email) values('zss','123',21,'女','1434278632@qq.com');
insert into music(title, singer, time, url, userId) values ('南方姑娘','赵雷','2020-02-02 ','music\\南方姑娘',1);
insert into lovemusic(user_id, music_id) values (1,2);

select music.id,title,singer,time,url,userId from lovemusic,music where lovemusic.user_id = music.userId;
select music.id,title,singer,time,url,userId from lovemusic,music where lovemusic.user_id = music.userId and title like '%予%';
insert into music(title, singer, time, url, userId) values ('四季予你','程响','2020-02-02 ','music//四季予你',1);