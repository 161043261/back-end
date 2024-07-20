package com.bronya.mybatisdemo;

import com.bronya.mybatisdemo.mapper.DeptMapper;
import com.bronya.mybatisdemo.pojo.Dept;
import com.bronya.mybatisdemo.util.MapperUtil;
import org.junit.jupiter.api.Test;

import static com.bronya.mybatisdemo.util.Colors.GREEN;
import static com.bronya.mybatisdemo.util.Colors.RESET;

public class DeptTest {
    @Test
    public void testGetDeptAndEmpList() {
        DeptMapper mapper = MapperUtil.getMapper(DeptMapper.class);
        Dept dept = mapper.getDeptAndEmpList(1);
        System.out.println(GREEN + dept + RESET);
    }

    @Test
    public void testGetDeptAndEmpListByStep() {
        DeptMapper mapper = MapperUtil.getMapper(DeptMapper.class);
        Dept dept = mapper.getDeptAndEmpListByStep(1);
        System.out.println(GREEN + dept.getDeptName() + RESET);
    }
}
