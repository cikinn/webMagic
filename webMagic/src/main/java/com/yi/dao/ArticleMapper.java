package com.yi.dao;

import com.yi.entity.Article;

import java.util.List;

public interface ArticleMapper {

    List<Article> findAll();

    void addArticle(Article article);
}
