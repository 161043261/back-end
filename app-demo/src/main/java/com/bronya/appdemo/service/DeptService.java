package com.bronya.appdemo.service;

import com.bronya.appdemo.pojo.Dept;

import java.util.List;

public interface DeptService {
    List<Dept> selectDeptList();

    int deleteDeptById(int id);

    int insertDept(Dept dept);

    Dept selectDeptById(int id);

    int updateDept(Dept dept);
}
