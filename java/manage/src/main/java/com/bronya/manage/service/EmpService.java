package com.bronya.manage.service;

import com.bronya.manage.pojo.Emp;
import com.bronya.manage.pojo.PageBean;

import java.time.LocalDate;

public interface EmpService {
    int deleteEmpList(int[] idList);

    int insertEmp(Emp emp);

    Emp getEmpById(int id);

    int updateEmp(Emp emp);

    PageBean<Emp> getEmpPage(int page, int pageSize, String name, Short gender, LocalDate begin, LocalDate end);

    Emp getEmpByUp(Emp emp);
}
