package com.bronya.projdemo.mapper;

import com.bronya.projdemo.dao.Category;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CategoryMapper {

    @Insert("insert into category (category_name, create_user, create_time, update_time) values (#{categoryName}, #{createUser}, #{createTime}, #{updateTime})")
    int insertCategory(Category category);

    @Select("select * from category where create_user = #{id} order by update_time desc")
    List<Category> selectCategoryList(Integer id);

    @Select("select * from category where id = #{id}")
    Category selectCategoryById(Integer id);

    @Update("update category set category_name = #{categoryName}, update_time = #{updateTime} where id = #{id}")
    int updateCategory(Category category);

    @Delete("delete from category where id = #{id}")
    int deleteCategoryById(Integer id);
}
