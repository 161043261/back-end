package com.bronya.mybatisdemo;

import com.bronya.mybatisdemo.mapper.EmpMapper;
import com.bronya.mybatisdemo.pojo.Emp;
import com.bronya.mybatisdemo.util.MapperUtil;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.bronya.mybatisdemo.util.Colors.GREEN;
import static com.bronya.mybatisdemo.util.Colors.RESET;

public class EmpTests {
    @Test
    public void testGetEmpList() {
        EmpMapper mapper = MapperUtil.getMapper(EmpMapper.class);
        List<Emp> empList = mapper.getEmpList();
        for (Emp emp : empList) {
            System.out.println(GREEN + emp + RESET);
        }
    }

    @Test
    public void testGetEmpListByAlias() {
        EmpMapper mapper = MapperUtil.getMapper(EmpMapper.class);
        List<Emp> empList = mapper.getEmpListByAlias();
        for (Emp emp : empList) {
            System.out.println(GREEN + emp + RESET);
        }
    }

    @Test
    public void testGetEmpListByResultMap() {
        EmpMapper mapper = MapperUtil.getMapper(EmpMapper.class);
        List<Emp> empList = mapper.getEmpListByResultMap();
        for (Emp emp : empList) {
            System.out.println(GREEN + emp + RESET);
        }
    }

    @Test
    public void testGetEmpAndDept() {
        EmpMapper mapper = MapperUtil.getMapper(EmpMapper.class);
        Emp emp = mapper.getEmpAndDept(1);
        System.out.println(GREEN + emp + RESET);
    }

    @Test
    public void testGetEmpAndDeptByAssociation() {
        EmpMapper mapper = MapperUtil.getMapper(EmpMapper.class);
        Emp emp = mapper.getEmpAndDeptByAssociation(1);
        System.out.println(GREEN + emp + RESET);
    }

    @Test
    public void testGetEmpAndDeptByStep() {
        EmpMapper mapper = MapperUtil.getMapper(EmpMapper.class);
        Emp emp = mapper.getEmpAndDeptByStep(1);
        System.out.println(GREEN + emp.getEmpName() + RESET);
    }
}
