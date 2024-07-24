package com.bronya.manage.service.impl;

import com.bronya.manage.mapper.DeptMapper;
import com.bronya.manage.pojo.Dept;
import com.bronya.manage.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {

    private DeptMapper deptMapper;

    @Autowired
    public void setDeptMapper(DeptMapper deptMapper) {
        this.deptMapper = deptMapper;
    }

    @Override
    public List<Dept> selectAll() {
        return deptMapper.selectAll();
    }

    @Override
    public int deleteById(int id) {
        return deptMapper.deleteById(id);
    }

    @Override
    public int insertDept(Dept dept) {
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        return deptMapper.insertDept(dept);
    }

    @Override
    public Dept selectById(int id) {
        return deptMapper.selectById(id);
    }

    @Override
    public int updateDept(Dept dept) {
        dept.setUpdateTime(LocalDateTime.now());
        return deptMapper.updateDept(dept);
    }
}
