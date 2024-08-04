package com.bronya.projdemo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bronya.projdemo.dao.Article;
import com.bronya.projdemo.mapper.ArticleMapper;
import com.bronya.projdemo.service.ArticleService;
import com.bronya.projdemo.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    private ArticleMapper articleMapper;

    @Autowired
    public void setArticleMapper(ArticleMapper articleMapper) {
        this.articleMapper = articleMapper;
    }

    @Override
    public int insertArticle(Article article) {
        Map<String, Object> map = ThreadLocalUtil.get();
        article.setCreateUser((Integer) map.get("id"));
        article.setCreateTime(LocalDateTime.now());
        article.setUpdateTime(LocalDateTime.now());
        return articleMapper.insertArticle(article);
    }

    @Override
    public Page<Article> selectArticlePage(Integer pageNum, Integer pageSize, Integer categoryId, Integer state) {
        Page<Article> page = Page.of(pageNum, pageSize);
        page.addOrder(OrderItem.desc("update_time"));
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        if (categoryId != null) {
            queryWrapper.eq("category_id", categoryId);
        }
        if (state != null) {
            queryWrapper.eq("state", state);
        }
        return articleMapper.selectPage(page, queryWrapper);
    }

    @Override
    public int deleteArticleById(Integer id) {
        return articleMapper.deleteArticleById(id);
    }

    @Override
    public int updateArticle(Article article) {
        article.setUpdateTime(LocalDateTime.now());
        return articleMapper.updateArticle(article);
    }

    @Override
    public Article selectArticleById(Integer id) {
        return articleMapper.selectArticleById(id);
    }
}
