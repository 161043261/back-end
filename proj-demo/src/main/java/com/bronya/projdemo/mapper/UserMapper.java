package com.bronya.projdemo.mapper;

import com.bronya.projdemo.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {

    @Select("select * from user where id = #{id}")
    User selectUserById(int id);

    @Select("select * from user where username = #{username}")
    User selectUserByUsername(String username);

    @Insert("insert into user (username, password, create_time, update_time) values (#{username}, #{password}, #{createTime}, #{updateTime})")
    int insertUser(User user);

    @Update("update user set nickname = #{nickname}, email = #{email}, update_time = #{updateTime} where id = #{id}")
    int updateUser(User user);

    @Update("update user set avatar = #{avatarUrl}, update_time = now() where id = #{id}")
    int updateAvatar(String avatarUrl, int id);

    @Update("update user set password = #{newPwd} where id = #{id}")
    int updatePwd(int id, String newPwd);
}
