use bronya;

drop table if exists `t_user`;

create table `t_user`
(
    `id`    int not null auto_increment comment 'user id',
    `username`   varchar(20) comment 'username',
    `password`   varchar(20) comment 'password',
    `age`   int comment 'user age',
    `sex`   char(10) comment 'user sex', # Fixed-length string
    `email` varchar(20) comment 'user email', # Variable-length string
    primary key (`id`)
) engine = InnoDB
  default charset = utf8mb4
  collate = utf8mb4_0900_ai_ci;