package com.bronya.appdemo.controller;

import com.bronya.appdemo.annotation.LogAnnotation;
import com.bronya.appdemo.pojo.Emp;
import com.bronya.appdemo.pojo.PageBean;
import com.bronya.appdemo.pojo.Result;
import com.bronya.appdemo.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController {

    private EmpService empService;

    @Autowired // inject by constructor
    public EmpController(EmpService empService) {
        this.empService = empService;
    }

    @GetMapping
    public Result selectEmpList(@RequestParam(defaultValue = "1") int page,
                                @RequestParam(defaultValue = "10") int pageSize,
                                String name,
                                Short gender,
                                @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                                @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end) {
        log.info("page={} pageSize={} name={} gender={} begin={} end={}", page, pageSize, name, gender, begin, end);
        PageBean<Emp> pageBean = empService.selectEmpPage(page, pageSize, name, gender, begin, end);
        return Result.success(pageBean);
    }

    @LogAnnotation
    @DeleteMapping("/{idList}")
    public Result deleteEmpList(@PathVariable int[] idList) { // List<Integer> idList
        log.info("idList={}", idList);
        int rowCount = empService.deleteEmpList(idList);
        return Result.success(rowCount);
    }

    @LogAnnotation
    @PostMapping
    public Result insertEmp(@RequestBody Emp emp) {
        log.info("emp={}", emp);
        int rowCount = empService.insertEmp(emp);
        return Result.success(rowCount);
    }

    @GetMapping("/{id}")
    public Result selectEmpById(@PathVariable int id) {
        log.info("id={}", id);
        Emp emp = empService.selectEmpById(id);
        return Result.success(emp);
    }

    @LogAnnotation
    @PutMapping
    public Result updateEmp(@RequestBody Emp emp) {
        log.info("emp={}", emp);
        int rowCount = empService.updateEmp(emp);
        return Result.success(rowCount);
    }
}
