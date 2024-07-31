package com.bronya.projdemo.mapper;

import com.bronya.projdemo.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select("select * from user where username = #{username}")
    User selectUserByUsername(String username);

    @Insert("insert into user (username, password, create_time, update_time) values (#{username}, #{password}, now(), now())")
    int insertUser(User user);
}
