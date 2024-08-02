package com.bronya.projdemo.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bronya.projdemo.dao.Article;
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
    public Result<List<Article>> selectArticleList(Integer pageNum, Integer pageSize,
                                                   @RequestParam(required = false) Integer categoryId,
                                                   @RequestParam(required = false) Integer state) {
        Page<Article> articlePage = articleService.selectArticleList(pageNum, pageSize, categoryId, state);
        return Result.ok("Select Article List OK", articlePage.getRecords());
    }
}
