package com.example.touristguide.service;

import com.example.touristguide.dto.ArticlePresentationDto;
import com.example.touristguide.dto.CreateArticleDto;
import com.example.touristguide.repository.article.ArticleRepoInterface;

import javax.inject.Inject;

public class ArticleService {
    @Inject
    private ArticleRepoInterface articleRepoInterface;

    public void addArticle(CreateArticleDto createArticleDto){
        articleRepoInterface.addArticle(createArticleDto);
    }


    public void deleteArticle(int article_id){
        articleRepoInterface.deleteArticle(article_id);
    }

    public ArticlePresentationDto getOneArticle(int article_id){
        return articleRepoInterface.getOneArticle(article_id);
    }




}
