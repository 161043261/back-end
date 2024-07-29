package com.bronya.manage.service.impl;

import com.bronya.manage.mapper.DeptMapper;
import com.bronya.manage.mapper.EmpMapper;
import com.bronya.manage.pojo.Dept;
import com.bronya.manage.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class DeptServiceImpl implements DeptService {

    private DeptMapper deptMapper;
    private EmpMapper empMapper;

    @Autowired
    public void setDeptMapper(DeptMapper deptMapper) {
        this.deptMapper = deptMapper;
    }

    @Autowired
    public void setEmpMapper(EmpMapper empMapper) {
        this.empMapper = empMapper;
    }

    @Override
    public List<Dept> selectDeptList() {
        return deptMapper.selectDeptList();
    }

    @Override // transaction
    public int deleteDeptById(int id) {
        int rowCount = empMapper.deleteEmpByDeptId(id);
        log.info("rowCount={}", rowCount);
        return deptMapper.deleteDeptById(id);
    }

    @Override
    public int insertDept(Dept dept) {
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        return deptMapper.insertDept(dept);
    }

    @Override
    public Dept selectDeptById(int id) {
        return deptMapper.selectDeptById(id);
    }

    @Override
    public int updateDept(Dept dept) {
        dept.setUpdateTime(LocalDateTime.now());
        return deptMapper.updateDept(dept);
    }
}
