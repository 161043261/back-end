package com.bronya.mybatisdemo.mapper;

import com.bronya.mybatisdemo.pojo.Dept;
import org.apache.ibatis.annotations.Param;

public interface DeptMapper {
    Dept getEmpAndDeptByStep(@Param("did") int did); // methodName = "getEmpAndDeptByStep"

    Dept getDeptAndEmpList(@Param("did") int did);

    Dept getDeptAndEmpListByStep(@Param("did") int did);
}
