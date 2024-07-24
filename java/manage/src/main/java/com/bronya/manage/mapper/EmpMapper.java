package com.bronya.manage.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmpMapper {

    @Delete("delete from emp where id = #{id}")
    public int deleteById(Integer id);
}
