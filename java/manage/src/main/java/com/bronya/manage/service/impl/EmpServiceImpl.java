package com.bronya.manage.service.impl;

import com.bronya.manage.mapper.EmpMapper;
import com.bronya.manage.pojo.Emp;
import com.bronya.manage.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {
    private EmpMapper empMapper;

    @Autowired
    public void setEmpMapper(EmpMapper empMapper) {
        this.empMapper = empMapper;
    }

    @Override
    public List<Emp> getEmpList(int page, int pageSize, String name, Short gender, LocalDate begin, LocalDate end) {
        int startIndex = (page - 1) * pageSize;
        return empMapper.getEmpList(startIndex, pageSize, name, gender, begin, end);
    }

    @Override
    public int deleteEmpList(int[] idList) {
        return empMapper.deleteEmpList(idList);
    }

    @Override
    public int insertEmp(Emp emp) {
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        return empMapper.insertEmp(emp);
    }

    @Override
    public Emp getEmpById(int id) {
        return empMapper.getEmpById(id);
    }

    @Override
    public int updateEmp(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());
        return empMapper.updateEmp(emp);
    }
}
