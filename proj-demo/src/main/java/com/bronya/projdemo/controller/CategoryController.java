package com.bronya.projdemo.controller;

import com.bronya.projdemo.pojo.Category;
import com.bronya.projdemo.pojo.Result;
import com.bronya.projdemo.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    private CategoryService categoryService;

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public Result<String> insertCategory(@RequestBody @Valid Category category) {
        int rowCount = categoryService.insertCategory(category);
        return Result.success("Insert Category OK", "rowCount=" + rowCount);
    }

    @GetMapping
    public Result<List<Category>> selectCategories() {
        List<Category> categories = categoryService.selectCategories();
        return Result.success("Select Categories OK", categories);
    }
}
