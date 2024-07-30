package com.bronya.mybatisdemo.mapper;

import com.bronya.mybatisdemo.pojo.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PageMapper {
    List<Employee> getEmployeeListByPage(@Param("startIndex") int startIndex, @Param("pageSize") int pageSize);
}
