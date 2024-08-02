package com.bronya.projdemo.service.impl;

import com.bronya.projdemo.mapper.CategoryMapper;
import com.bronya.projdemo.pojo.Category;
import com.bronya.projdemo.service.CategoryService;
import com.bronya.projdemo.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryMapper categoryMapper;

    @Autowired
    public void setCategoryMapper(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    @Override
    public int insertCategory(Category category) {
        Map<String, Object> claims = ThreadLocalUtil.get();
        if (category.getCategoryAlias() == null) {
            category.setCategoryAlias(category.getCategoryName());
        }
        category.setCreateUser((int) claims.get("id"));
        category.setCreateTime(LocalDateTime.now());
        category.setUpdateTime(LocalDateTime.now());
        return categoryMapper.insertCategory(category);
    }

    @Override
    public List<Category> selectCategories() {
        Map<String, Object> claims = ThreadLocalUtil.get();

        return categoryMapper.selectCategories((int) claims.get("id"));
    }
}
