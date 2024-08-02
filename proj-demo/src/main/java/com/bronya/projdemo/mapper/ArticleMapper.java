package com.bronya.projdemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bronya.projdemo.dao.Article;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ArticleMapper extends BaseMapper<Article> {

    @Insert("insert into article (title, content, image, state, category_id, create_user, create_time, update_time) values (#{title}, #{content}, #{image}, #{state}, #{categoryId}, #{createUser}, #{createTime}, #{updateTime})")
    int insertArticle(Article article);
}
