package com.bronya.projdemo.mapper;

import com.bronya.projdemo.pojo.Article;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ArticleMapper {

    @Insert("insert into article (title, content, image, state, category_id, create_user, create_time, update_time) values (#{title}, #{content}, #{image}, #{state}, #{categoryId}, #{createUser}, #{createTime}, #{updateTime})")
    int insertArticle(Article article);
}
