package com.bronya.projdemo.controller;

import com.bronya.projdemo.pojo.Article;
import com.bronya.projdemo.pojo.Result;
import com.bronya.projdemo.service.ArticleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
