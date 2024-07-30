package com.bronya.appdemo.service;

import com.bronya.appdemo.pojo.Emp;
import com.bronya.appdemo.pojo.PageBean;

import java.time.LocalDate;

public interface EmpService {
    int deleteEmpList(int[] idList);

    int insertEmp(Emp emp);

    Emp selectEmpById(int id);

    int updateEmp(Emp emp);

    PageBean<Emp> selectEmpPage(int page, int pageSize, String name, Short gender, LocalDate begin, LocalDate end);

    Emp selectEmpByUp(Emp emp);
}
