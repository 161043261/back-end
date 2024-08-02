package com.bronya.projdemo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bronya.projdemo.dao.Article;

public interface ArticleService {
    int insertArticle(Article article);

    Page<Article> selectArticleList(Integer pageNum, Integer pageSize, Integer categoryId, Integer state);
}
