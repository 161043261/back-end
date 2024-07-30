package com.bronya.appdemo.service.impl;

import com.bronya.appdemo.mapper.DeptMapper;
import com.bronya.appdemo.mapper.EmpMapper;
import com.bronya.appdemo.pojo.Dept;
import com.bronya.appdemo.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class DeptServiceImpl implements DeptService {

    private DeptMapper deptMapper;
    private EmpMapper empMapper;

    @Autowired // inject by setter
    public void setDeptMapper(DeptMapper deptMapper) {
        this.deptMapper = deptMapper;
    }

    @Autowired // inject by setter
    public void setEmpMapper(EmpMapper empMapper) {
        this.empMapper = empMapper;
    }

    @Override
    public List<Dept> selectDeptList(/* no args */) {
        return deptMapper.selectDeptList();
    }

    // ***** rollbackFor *****
    // default: rollback if a RuntimeException occurs
    // ***** propagation *****
    // 1. Propagation.REQUIRED (default)
    // 2. Propagation.REQUIRES_NEW
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    @Override
    public int deleteDeptById(int id) {
        // start transaction (begin)
        int rowCount = empMapper.deleteEmpByDeptId(id);
        log.info("rowCount={}", rowCount);
        return deptMapper.deleteDeptById(id);
        // error ? rollback : commit
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
