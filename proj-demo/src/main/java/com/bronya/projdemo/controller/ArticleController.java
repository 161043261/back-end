package com.bronya.projdemo.controller;

import com.bronya.projdemo.mapper.ArticleMapper;
import com.bronya.projdemo.pojo.Article;
import com.bronya.projdemo.pojo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/article")
public class ArticleController {

    private ArticleMapper articleMapper;

    @Autowired
    public void setArticleMapper(ArticleMapper articleMapper) {
        this.articleMapper = articleMapper;
    }

    @GetMapping("/list")
    public Result<List<Article>> list() {
        return Result.success(new ArrayList<>());
    }
}
