package com.bronya.manage.service;

import com.bronya.manage.pojo.Emp;

import java.time.LocalDate;
import java.util.List;

public interface EmpService {
    List<Emp> getEmpList(int page, int pageSize, String name, Short gender, LocalDate begin, LocalDate end);

    int deleteEmpList(int[] idList);

    int insertEmp(Emp emp);

    Emp getEmpById(int id);

    int updateEmp(Emp emp);
}
