package com.bronya.appdemo.service.impl;

import com.bronya.appdemo.mapper.EmpMapper;
import com.bronya.appdemo.pojo.Emp;
import com.bronya.appdemo.pojo.PageBean;
import com.bronya.appdemo.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {
    private EmpMapper empMapper;

    @Autowired // inject by setter
    public void setEmpMapper(EmpMapper empMapper) {
        this.empMapper = empMapper;
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
    public Emp selectEmpById(int id) {
        return empMapper.selectEmpById(id);
    }

    @Override
    public int updateEmp(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());
        return empMapper.updateEmp(emp);
    }

    @Override
    public PageBean<Emp> selectEmpPage(int page, int pageSize, String name, Short gender, LocalDate begin, LocalDate end) {
        int startIndex = (page - 1) * pageSize;
        int empCount = empMapper.selectEmpCnt(name, gender, begin, end);
        List<Emp> empList = empMapper.selectEmpPage(startIndex, pageSize, name, gender, begin, end);
        PageBean<Emp> pageBean = new PageBean<>();
        pageBean.setTotal(empCount);
        pageBean.setRows(empList);
        return pageBean;
    }

    @Override
    public Emp selectEmpByUp(Emp emp) {
        return empMapper.selectEmpByUp(emp);
    }
}
