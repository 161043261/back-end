use bronya;

drop table if exists `t_user`;

create table `t_user`
(
    `user_id`    int not null auto_increment comment 'user id',
    `username`   varchar(20) comment 'username',
    `password`   varchar(20) comment 'password',
    `user_age`   int comment 'user age',
    `user_sex`   char(10) comment 'user sex', # Fixed-length string
    `user_email` varchar(20) comment 'user email', # Variable-length string
    primary key (`user_id`)
) engine = InnoDB
  default charset = utf8mb4
  collate = utf8mb4_0900_ai_ci;