package com.bronya.projdemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bronya.projdemo.dao.Article;
import org.apache.ibatis.annotations.*;

@Mapper
public interface ArticleMapper extends BaseMapper<Article> {

    @Insert("insert into article (title, content, image, state, category_id, create_user, create_time, update_time) values (#{title}, #{content}, #{image}, #{state}, #{categoryId}, #{createUser}, #{createTime}, #{updateTime})")
    int insertArticle(Article article);

    @Delete("delete from article where category_id = #{id}")
    int deleteArticleByCategoryId(Integer id);

    @Delete("delete from article where id = #{id}")
    int deleteArticleById(Integer id);

    @Update("update article set title = #{title}, content = #{content}, image = #{image}, state = #{state}, category_id = #{categoryId} where id = #{id}")
    int updateArticle(Article article);

    @Select("select * from article where id = #{id}")
    Article selectArticleById(Integer id);
}
