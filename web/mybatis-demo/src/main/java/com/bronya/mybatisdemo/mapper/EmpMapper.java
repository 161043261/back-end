package com.bronya.mybatisdemo.mapper;

import com.bronya.mybatisdemo.pojo.Employee;
import org.apache.ibatis.annotations.Param;

public interface EmpMapper {
    Employee getEmpByParam(@Param("emp_name") String empName);
}
