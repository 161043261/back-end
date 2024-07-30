package com.bronya.mybatisdemo;

import org.junit.jupiter.api.Test;

import com.bronya.mybatisdemo.mapper.DeptMapper;
import com.bronya.mybatisdemo.pojo.Dept;
import com.bronya.mybatisdemo.util.MapperUtil;

public class DeptTests {
    @Test
    public void testGetDeptAndEmpList() {
        DeptMapper mapper = MapperUtil.getMapper(DeptMapper.class);
        Dept dept = mapper.getDeptAndEmpList(1);
        System.out.println(dept);
    }

    @Test
    public void testGetDeptAndEmpListByStep() {
        DeptMapper mapper = MapperUtil.getMapper(DeptMapper.class);
        Dept dept = mapper.getDeptAndEmpListByStep(1);
        System.out.println(dept.getDeptName());
    }
}
