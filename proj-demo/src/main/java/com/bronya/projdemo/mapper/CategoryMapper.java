package com.bronya.projdemo.mapper;

import com.bronya.projdemo.pojo.Category;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CategoryMapper {

    @Insert("insert into category (category_name, category_alias, create_user, create_time, update_time) values (#{categoryName}, #{categoryAlias}, #{createUser}, #{createTime}, #{updateTime})")
    int insertCategory(Category category);

    @Select("select * from category where create_user = #{id}")
    List<Category> selectCategories(int id);
}
