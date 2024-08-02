package com.bronya.projdemo.service;

import com.bronya.projdemo.dao.Category;

import java.util.List;

public interface CategoryService {
    int insertCategory(Category category);

    List<Category> selectCategoryList();

    Category selectCategoryById(Integer id);

    int updateCategory(Category category);

    int deleteCategoryById(Integer id);
}
