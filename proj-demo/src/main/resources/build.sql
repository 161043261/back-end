create database if not exists bronya;

use bronya;

drop table if exists article;
drop table if exists category;
drop table if exists user;

create table user
(
    id          int unsigned primary key auto_increment comment 'user id',
    username    varchar(32) not null unique comment '用户名',
    password    varchar(32) comment 'username',
    nickname    varchar(32)  default '' comment 'nickname',
    email       varchar(64)  default '' comment 'email',
    avatar      varchar(128) default '' comment 'avatar',
    create_time datetime    not null comment 'create time',
    update_time datetime    not null comment 'update time'
) comment 'user table';

create table category
(
    id             int unsigned primary key auto_increment comment 'category id',
    category_name  varchar(32)  not null comment 'category name',
    category_alias varchar(32)  not null comment 'category alias',
    create_user    int unsigned not null comment 'create user',
    create_time    datetime     not null comment 'create time',
    update_time    datetime     not null comment 'update time',
    constraint fk_category_user foreign key (create_user) references user (id)
) comment 'category table';


create table article
(
    id          int unsigned primary key auto_increment comment 'ID',
    title       varchar(32)   not null comment 'article title',
    content     varchar(5120) not null comment 'article content',
    cover_img   varchar(32)   not null comment 'article cover image',
    state       varchar(32) default 'beta' comment 'article state: beta or release',
    category_id int unsigned comment 'article category id',
    create_user int unsigned  not null comment 'create user id',
    create_time datetime      not null comment 'create time',
    update_time datetime      not null comment 'update time',
    constraint fk_article_category foreign key (category_id) references category (id),
    constraint fk_article_user foreign key (create_user) references user (id)
) comment 'article table';