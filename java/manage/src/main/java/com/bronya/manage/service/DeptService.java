package com.bronya.manage.service;

import com.bronya.manage.pojo.Dept;

import java.util.List;

public interface DeptService {
    List<Dept> selectDeptList();

    int deleteDeptById(int id);

    int insertDept(Dept dept);

    Dept selectDeptById(int id);

    int updateDept(Dept dept);
}
