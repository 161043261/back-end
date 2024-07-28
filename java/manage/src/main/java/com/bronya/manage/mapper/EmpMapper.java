package com.bronya.manage.mapper;

import com.bronya.manage.pojo.Emp;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface EmpMapper {
    int getEmpCount(String name, Short gender, LocalDate begin, LocalDate end);

    List<Emp> getEmpPage(int startIndex, int pageSize, String name, Short gender, LocalDate begin, LocalDate end);

    int deleteEmpList(int[] idList);

    @Insert("insert into emp (username, name, gender, image, job, entrydate, dept_id, create_time, update_time)" +
            "values (#{username}, #{name}, #{gender}, #{image}, #{job}, #{entrydate}, #{deptId}, #{createTime}, #{updateTime})")
    int insertEmp(Emp emp);

    @Select("select * from emp where id = #{id}")
    Emp getEmpById(int id);

    int updateEmp(Emp emp);

    @Select("select * from emp where username = #{username} and password = #{password}")
    Emp getEmpByUp(Emp emp);
}
