package com.bronya.projdemo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bronya.projdemo.dao.Article;

public interface ArticleService {
    int insertArticle(Article article);

    Page<Article> selectArticlePage(Integer pageNum, Integer pageSize, Integer categoryId, Integer state);

    int deleteArticleById(Integer id);

    int updateArticle(Article article);

    Article selectArticleById(Integer id);
}
