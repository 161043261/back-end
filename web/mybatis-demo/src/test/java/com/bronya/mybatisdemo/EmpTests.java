package com.bronya.mybatisdemo;

import com.bronya.mybatisdemo.mapper.EmpMapper;
import com.bronya.mybatisdemo.pojo.Employee;
import com.bronya.mybatisdemo.util.MapperUtil;

public class EmpTests {
    // @Test
    public void testGetByParam() {
        EmpMapper mapper = MapperUtil.getMapper(EmpMapper.class);
        Employee employee = mapper.getEmpByParam("Yoimiya");
        System.out.println(employee + "\n");
    }
}
