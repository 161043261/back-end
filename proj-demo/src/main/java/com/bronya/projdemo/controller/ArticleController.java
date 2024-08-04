package com.bronya.projdemo.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bronya.projdemo.dao.Article;
import com.bronya.projdemo.dao.PageBean;
import com.bronya.projdemo.dao.Result;
import com.bronya.projdemo.service.ArticleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/article")
public class ArticleController {

    private ArticleService articleService;

    @Autowired
    public void setArticleMapper(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping
    public Result<String> insertArticle(@RequestBody @Valid Article article) {
        int rowCount = articleService.insertArticle(article);
        return Result.ok("Insert Article OK", "rowCount=" + rowCount);
    }

    @GetMapping
    public Result<PageBean<Article>> selectArticlePage(Integer pageNum, Integer pageSize,
                                                       @RequestParam(required = false) Integer categoryId,
                                                       @RequestParam(required = false) Integer state) {
        Page<Article> articlePage = articleService.selectArticlePage(pageNum, pageSize, categoryId, state);
        long total = articlePage.getTotal();
        List<Article> articleList = articlePage.getRecords();
        PageBean<Article> pageBean = new PageBean<>(total, articleList);
        return Result.ok("Select Article List OK", pageBean);
    }


    @DeleteMapping
    public Result<String> deleteArticle(Integer id) {
        int rowCount = articleService.deleteArticleById(id);
        return Result.ok("Delete Article OK", "rowCount=" + rowCount);
    }

    @PutMapping
    public Result<String> updateArticle(@RequestBody @Valid Article article) {
        int rowCount = articleService.updateArticle(article);
        return Result.ok("Update Article OK", "rowCount=" + rowCount);
    }

    @GetMapping("/detail")
    public Result<Article> selectArticle(Integer id) {
        Article article = articleService.selectArticleById(id);
        return Result.ok("Select Article OK", article);
    }
}
