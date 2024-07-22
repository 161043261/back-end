package com.bronya.mybatisdemo.mapper;

import com.bronya.mybatisdemo.pojo.Emp;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DynamicMapper {
    List<Emp> getEmpDynamic1(Emp emp); // test where, if

    List<Emp> getEmpDynamic2(Emp emp); // test trim

    List<Emp> getEmpDynamic3(Emp emp); // test choose, when, otherwise

    // default: mybatisMap["array"] = mybatisMap["arg0"] = eidList
    // available: mybatisMap["eidList"] = mybatisMap["param1"] = eidList
    int deleteEmpDynamic1(@Param("eidList") int[] eidList); // test foreach

    int deleteEmpDynamic2(@Param("eidList") int[] eidList); // test foreach

    // default: mybatisMap["arg0"] = mybatisMap["collection"] = mybatisMap["list"] = empList
    // available: mybatisMap["empList"] = mybatisMap["param1"] = empList
    int insertEmpDynamic(@Param("empList") List<Emp> empList); // test foreach
}
