package com.bronya.projdemo.controller;

import com.bronya.projdemo.dao.Category;
import com.bronya.projdemo.dao.Result;
import com.bronya.projdemo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
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
    public Result<String> insertCategory(@RequestBody @Validated(Category.Insert.class) Category category) {
        int rowCount = categoryService.insertCategory(category);
        return Result.ok("Insert Category OK", "rowCount=" + rowCount);
    }

    @GetMapping
    public Result<List<Category>> selectCategoryList() {
        List<Category> categoryList = categoryService.selectCategoryList();
        return Result.ok("Select Category List OK", categoryList);
    }

    @GetMapping("/detail")
    public Result<Category> selectCategory(Integer id) {
        Category category = categoryService.selectCategoryById(id);
        return Result.ok("Get Category Detail OK", category);
    }

    @PutMapping
    public Result<String> updateCategory(@RequestBody @Validated(Category.Update.class) Category category) {
        int rowCount = categoryService.updateCategory(category);
        return Result.ok("Update Category OK", "rowCount=" + rowCount);
    }

    @DeleteMapping
    public Result<String> deleteCategory(Integer id) {
        int rowCount = categoryService.deleteCategoryById(id);
        return Result.ok("Delete Category OK", "rowCount=" + rowCount);
    }
}
