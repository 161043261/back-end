package com.bronya.manage.controller;

import com.bronya.manage.pojo.Dept;
import com.bronya.manage.pojo.Result;
import com.bronya.manage.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.bronya.manage.utils.Colors.BLUE;
import static com.bronya.manage.utils.Colors.RESET;

@Slf4j // automatically generate line18
@RestController
public class DeptController {
    // private static Logger log = LoggerFactory.getLogger(DeptController.class);

    // TODO ***** 1. autowired (injected) by field *****
    /* @Autowired
    private DeptService deptService; */

    // TODO ***** 2. autowired (injected) by constructor *****
    /* private DeptController deptController;
    @Autowired
    public DeptController(DeptController deptController) {
        this.deptController = deptController;
    } */

    // TODO ***** 3. autowired (injected) by setter *****
    private DeptService deptService;

    @Autowired
    public void setDeptService(DeptService deptService) {
        this.deptService = deptService;
    }

    @RequestMapping(value = "/depts", method = RequestMethod.GET)
    public Result selectAll() {
        System.out.println(BLUE + "controller" + RESET);
        log.info("select * from dept");
        List<Dept> deptList = deptService.selectAll();
        return Result.success(deptList);
    }

    /**
     * public @interface PathVariable
     * Annotation which indicates that a method parameter should be bound to a URI template variable.
     *
     * @param id number
     * @return effected row count
     */
    @DeleteMapping("/depts/{id}")
    public Result deleteById(@PathVariable int id) {
        log.info("id={}", id);
        int rowCount = deptService.deleteById(id);
        return Result.success(rowCount);
    }

    /**
     * public @interface RequestBody
     * Annotation indicating a method parameter should be bound to the body of the web request.
     *
     * @param dept json
     * @return effected row count
     */
    @PostMapping("/depts")
    public Result insertDept(@RequestBody Dept dept) {
        log.info("dept={})", dept);
        int rowCount = deptService.insertDept(dept);
        return Result.success(rowCount);
    }

    @GetMapping("/depts/{id}")
    public Result selectById(@PathVariable int id) {
        log.info("id={}", id);
        Dept dept = deptService.selectById(id);
        return Result.success(dept);
    }

    @PutMapping("/depts")
    public Result updateDept(@RequestBody Dept dept) {
        log.info("dept={}", dept);
        int rowCount = deptService.updateDept(dept);
        return Result.success(rowCount);
    }
}
