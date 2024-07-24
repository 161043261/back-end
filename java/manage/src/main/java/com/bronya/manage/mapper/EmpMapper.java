package com.bronya.manage.mapper;

import com.bronya.manage.pojo.Emp;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface EmpMapper {
    List<Emp> getEmpList(int startIndex, int pageSize, String name, Short gender, LocalDate begin, LocalDate end);

    int deleteEmpList(int[] idList);

    @Insert("insert into emp (username, password, name, gender, image, job, entrydate, create_time, update_time) " +
            "values (#{username}, #{password}, #{name}, #{gender}, #{image}, #{job}, #{entrydate}, #{createTime}, #{updateTime})")
    int insertEmp(Emp emp);

    @Select("select * from emp where id = #{id}")
    Emp getEmpById(int id);

    int updateEmp(Emp emp);
}
