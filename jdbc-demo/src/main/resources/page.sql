# create database bronya;
use bronya;

drop table if exists t_emp;

create table t_emp
(
    emp_id     int auto_increment comment 'employee id' primary key,
    emp_name   varchar(100)  not null comment 'employee name',
    emp_salary double(10, 5) not null comment 'employee salary',
    emp_age    int           not null comment 'employee age'
);


insert into t_emp (emp_name, emp_salary, emp_age)
values ('Klee', 111.1, 1),
       ('Yoimiya', 222.2, 2),
       ('Ganyu', 333.3, 3),
       ('Hutao', 444.4, 4);
