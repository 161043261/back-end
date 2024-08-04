package com.bronya.projdemo.mapper;

import com.bronya.projdemo.dao.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {

    @Select("select * from user where id = #{id}")
    User selectUserById(Integer id);

    @Select("select * from user where username = #{username}")
    User selectUserByUsername(String username);

    @Insert("insert into user (username, password, create_time, update_time) values (#{username}, #{password}, #{createTime}, #{updateTime})")
    int insertUser(User user);

    @Update("update user set name = #{name}, email = #{email}, update_time = #{updateTime} where id = #{id}")
    int updateUser(User user);

    @Update("update user set avatar = #{avatar}, update_time = now() where id = #{id}")
    int updateAvatar(String avatar, int id);

    @Update("update user set password = #{newPwd} where id = #{id}")
    int updatePwd(int id, String newPwd);
}
