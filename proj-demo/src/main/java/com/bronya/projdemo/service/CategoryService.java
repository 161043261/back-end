package com.bronya.projdemo.service;

import com.bronya.projdemo.pojo.Category;

import java.util.List;

public interface CategoryService {
    int insertCategory(Category category);

    List<Category> selectCategories();
}
