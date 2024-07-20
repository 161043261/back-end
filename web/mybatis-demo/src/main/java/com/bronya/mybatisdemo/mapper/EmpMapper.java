package com.bronya.mybatisdemo.mapper;

import com.bronya.mybatisdemo.pojo.Emp;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmpMapper {
    List<Emp> getEmpList();

    List<Emp> getEmpListByAlias();

    List<Emp> getEmpListByResultMap();

    Emp getEmpAndDept(@Param("eid") int eid);

    Emp getEmpAndDeptByAssociation(@Param("eid") int eid);

    Emp getEmpAndDeptByStep(@Param("eid") int eid);
}
