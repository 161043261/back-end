create database if not exists bronya;
use bronya;

drop table if exists dept;
create table dept
(
    id          int unsigned primary key auto_increment comment 'primary key id',
    name        varchar(30) not null unique comment 'department name',
    create_time datetime    not null comment 'create timestamp',
    update_time datetime    not null comment 'update timestamp'
) comment 'department table';

insert into dept (id, name, create_time, update_time)
values (1, 'student affairs', now(), now()),
       (2, 'teaching and research', now(), now()),
       (3, 'consulting', now(), now()),
       (4, 'employment', now(), now()),
       (5, 'human resources', now(), now());

drop table if exists emp;
create table emp
(
    id          int unsigned primary key auto_increment comment 'primary key id',
    username    varchar(20)      not null unique comment 'username',
    password    varchar(32) default '123456' comment 'password',
    name        varchar(10)      not null comment 'actual name',
    gender      tinyint unsigned not null comment 'gender, 1 as male, 2 as female',
    image       varchar(300) comment 'image',
    job         tinyint unsigned comment 'job,
1 as teacher, 2 as lecturer, 3 as student affairs supervisor, 4 as teaching and research supervisor, 5 as consultant',
    entrydate   date comment 'entry date',
    dept_id     int unsigned comment 'department id',
    create_time datetime         not null comment 'create time',
    update_time datetime         not null comment 'update time'
) comment 'employee table';

insert into emp
(id, username, password, name, gender, image, job, entrydate, dept_id, create_time, update_time)
values (1, 'emp_a', '123456', 'emp_a', 1, '1.jpg', 4, '2000-01-01', 2, now(), now()),
       (2, 'emp_b', '123456', 'emp_b', 1, '2.jpg', 2, '2015-01-01', 2, now(), now()),
       (3, 'emp_c', '123456', 'emp_c', 1, '3.jpg', 2, '2008-05-01', 2, now(), now()),
       (4, 'emp_d', '123456', 'emp_d', 1, '4.jpg', 2, '2007-01-01', 2, now(), now()),
       (5, 'emp_e', '123456', 'emp_e', 1, '5.jpg', 2, '2012-12-05', 2, now(), now()),
       (6, 'emp_f', '123456', 'emp_f', 2, '6.jpg', 3, '2013-09-05', 1, now(), now()),
       (7, 'emp_g', '123456', 'emp_g', 2, '7.jpg', 1, '2005-08-01', 1, now(), now()),
       (8, 'emp_h', '123456', 'emp_h', 2, '8.jpg', 1, '2014-11-09', 1, now(), now()),
       (9, 'emp_i', '123456', 'emp_i', 2, '9.jpg', 1, '2011-03-11', 1, now(), now()),
       (10, 'emp_j', '123456', 'emp_j', 2, '10.jpg', 1, '2013-09-05', 1, now(), now()),
       (11, 'emp_k', '123456', 'emp_k', 1, '11.jpg', 5, '2007-02-01', 3, now(), now()),
       (12, 'emp_l', '123456', 'emp_l', 1, '12.jpg', 5, '2008-08-18', 3, now(), now()),
       (13, 'emp_m', '123456', 'emp_m', 1, '13.jpg', 5, '2012-11-01', 3, now(), now()),
       (14, 'emp_n', '123456', 'emp_n', 1, '14.jpg', 2, '2002-08-01', 2, now(), now()),
       (15, 'emp_o', '123456', 'emp_o', 1, '15.jpg', 2, '2011-05-01', 2, now(), now()),
       (16, 'emp_p', '123456', 'emp_p', 1, '16.jpg', 2, '2007-01-01', 2, now(), now()),
       (17, 'emp_q', '123456', 'emp_q', 1, '17.jpg', NULL, '2015-03-21', NULL, now(), now());

drop table if exists operate_log;
create table operate_log
(
    id             int unsigned primary key auto_increment comment 'id',
    operate_user   int unsigned comment 'operate user (id)',
    operate_time   datetime comment 'operate time',
    class_name     varchar(100) comment 'class name',
    method_name    varchar(100) comment 'method name',
    args           varchar(1000) comment 'arguments',
    return_value   varchar(2000) comment 'return value',
    benchmark_time bigint comment 'benchmark time (ms)'
) comment 'operate log table';
