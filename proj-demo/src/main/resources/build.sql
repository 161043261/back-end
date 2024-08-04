create database if not exists bronya;

use bronya;

drop table if exists article;
drop table if exists category;
drop table if exists user;

create table user
(
    id          int unsigned primary key auto_increment comment 'id',
    username    varchar(32) not null unique comment 'username',
    password    varchar(32) comment 'password',
    name        varchar(32)  default '' comment 'name',
    email       varchar(64)  default '' comment 'email',
    avatar      varchar(128) default '' comment 'avatar',
    create_time datetime    not null comment 'create time',
    update_time datetime    not null comment 'update time'
) comment 'user table';

create table category
(
    id            int unsigned primary key auto_increment comment 'id',
    category_name varchar(32)  not null comment 'name',
    create_user   int unsigned not null comment 'create user',
    create_time   datetime     not null comment 'create time',
    update_time   datetime     not null comment 'update time',
    constraint fk_category_user foreign key (create_user) references user (id)
) comment 'category';


create table article
(
    id          int unsigned primary key auto_increment comment 'id',
    title       varchar(32)   not null comment 'title',
    content     varchar(5120) not null comment 'content',
    image       varchar(128)  not null comment 'image',
    state       int default 0 comment 'state: 0 as BETA, 1 as RELEASE',
    category_id int unsigned comment 'category id',
    create_user int unsigned  not null comment 'create user id',
    create_time datetime      not null comment 'create time',
    update_time datetime      not null comment 'update time',
    constraint fk_article_category foreign key (category_id) references category (id),
    constraint fk_article_user foreign key (create_user) references user (id)
) comment 'article';