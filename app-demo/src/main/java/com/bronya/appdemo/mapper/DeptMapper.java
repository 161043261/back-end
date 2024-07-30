package com.bronya.appdemo.mapper;

import com.bronya.appdemo.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DeptMapper {

    @Select("select * from dept")
    List<Dept> selectDeptList();

    @Delete("delete from dept where id = #{id}")
    int deleteDeptById(int id);

    @Insert("insert into dept (name, create_time, update_time) values (#{name}, #{createTime}, #{updateTime})")
    int insertDept(Dept dept);

    @Result(column = "create_time", property = "createTime")
    @Result(column = "update_time", property = "updateTime")
    // @Select("select id, name, create_time as createTime, update_time as updateTime from dept where id = #{id}")
    @Select("select id, name, create_time, update_time from dept where id = #{id}")
    Dept selectDeptById(int id);

    @Update("update dept set name = #{name}, update_time = #{updateTime} where id = #{id}")
    int updateDept(Dept dept);
}
