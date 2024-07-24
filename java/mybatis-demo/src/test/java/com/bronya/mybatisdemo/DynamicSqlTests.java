package com.bronya.mybatisdemo;

import com.bronya.mybatisdemo.mapper.DynamicMapper;
import com.bronya.mybatisdemo.pojo.Emp;
import com.bronya.mybatisdemo.util.MapperUtil;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static com.bronya.mybatisdemo.util.Colors.GREEN;
import static com.bronya.mybatisdemo.util.Colors.RESET;

public class DynamicSqlTests {
    @Test // test where, if
    public void testGetEmpDynamic1() {
        DynamicMapper mapper = MapperUtil.getMapper(DynamicMapper.class);
        List<Emp> empList = mapper.getEmpDynamic1(new Emp(null, "Hutao", 2, "female", "hutao@bronya.com", null));
        System.out.println(GREEN + empList + RESET);
    }

    @Test // test trim
    public void testGetEmpDynamic2() {
        DynamicMapper mapper = MapperUtil.getMapper(DynamicMapper.class);
        List<Emp> empList = mapper.getEmpDynamic2(new Emp(null, "Hutao", 2, "female", "hutao@bronya.com", null));
        System.out.println(GREEN + empList + RESET);
    }

    @Test // test choose, when, otherwise
    public void testGetEmpDynamic3() {
        DynamicMapper mapper = MapperUtil.getMapper(DynamicMapper.class);
        List<Emp> empList = mapper.getEmpDynamic3(new Emp(null, "Hutao", 2, "female", "hutao@bronya.com", null));
        System.out.println(GREEN + empList + RESET);
    }

    @Test // test foreach
    public void testDeleteEmpDynamic1() {
        DynamicMapper mapper = MapperUtil.getMapper(DynamicMapper.class);
        int rowCount = mapper.deleteEmpDynamic1(new int[]{1, 2, 3});
        System.out.println(GREEN + "rowCount = " + rowCount + RESET);
    }

    @Test // test foreach
    public void testDeleteEmpDynamic2() {
        DynamicMapper mapper = MapperUtil.getMapper(DynamicMapper.class);
        int rowCount = mapper.deleteEmpDynamic2(new int[]{1, 2, 3});
        System.out.println(GREEN + "rowCount = " + rowCount + RESET);
    }

    @Test // test foreach
    public void testInsertEmpDynamic() {
        DynamicMapper mapper = MapperUtil.getMapper(DynamicMapper.class);
        Emp emp1 = new Emp(null, "Klee", 1, "female", "klee@bronya.com", null);
        Emp emp2 = new Emp(null, "Hutao", 2, "female", "hutao@bronya.com", null);
        Emp emp3 = new Emp(null, "Ganyu", 3, "female", "ganyu@bronya.com", null);
        Emp emp4 = new Emp(null, "Yoimiya", 4, "female", "yoimiya@bronya.com", null);
        Emp emp5 = new Emp(null, "Nilou", 5, "female", "nilou@bronya.com", null);
        // Arrays.asList(...)
        List<Emp> empList = Arrays.asList(emp1, emp2, emp3, emp4, emp5);
        int rowCount = mapper.insertEmpDynamic(empList);
        System.out.println(GREEN + "rowCount = " + rowCount + RESET);
    }
}
