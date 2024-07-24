create database if not exists `bronya`;

use `bronya`;

#
# t_user
#
drop table if exists `t_user`;

create table `t_user`
(
    `id`       int not null auto_increment comment 'user id',
    `username` varchar(20) comment 'username',
    `password` varchar(20) comment 'password',
    `age`      int comment 'user age',
    `sex`      char(6) comment 'user sex',       # Fixed-length string
    `email`    varchar(20) comment 'user email', # Variable-length string
    primary key (`id`)
) engine = InnoDB
  default charset = utf8mb4;

insert into t_user (username, password, age, sex, email)
values ('admin', '1024', 1, 'male', 'admin@bronya.com'),
       ('root', '2048', 2, 'female', 'root@bronya.com');

#
# t_dept
#
drop table if exists `t_dept`;

create table `t_dept`
(
    `did`       int not null auto_increment comment 'department id',
    `dept_name` varchar(20) comment 'department name',
    primary key (`did`)
) engine = InnoDB
  default charset = utf8mb4;

insert into `t_dept` (dept_name) value ('frontend'),('backend'),('devops');

#
# t_emp
#
drop table if exists `t_emp`;

create table `t_emp`
(
    `eid`      int not null auto_increment comment 'employee id',
    `emp_name` varchar(20) comment 'employee name',
    `age`      int comment 'employee age',
    `sex`      char(6) comment 'employee sex',       # Fixed-length string
    `email`    varchar(20) comment 'employee email', # Variable-length string
    `did`      int comment 'department id',
    primary key (`eid`)
) engine = InnoDB
  default charset = utf8mb4;

insert into t_emp (emp_name, age, sex, email, did)
values ('Klee', 1, 'female', 'klee@bronya.com', 1),
       ('Hutao', 2, 'female', 'hutao@bronya.com', 2),
       ('Ganyu', 3, 'female', 'ganyu@bronya.com', 3),
       ('Yoimiya', 4, 'female', 'yoimiya@bronya.com', 1),
       ('Nilou', 5, 'female', 'nilou@bronya.com', 2);
