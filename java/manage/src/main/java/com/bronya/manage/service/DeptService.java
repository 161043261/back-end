package com.bronya.manage.service;

import com.bronya.manage.pojo.Dept;

import java.util.List;

public interface DeptService {
    List<Dept> selectAll();

    int deleteById(int id);

    int insertDept(Dept dept);

    Dept selectById(int id);

    int updateDept(Dept dept);
}
