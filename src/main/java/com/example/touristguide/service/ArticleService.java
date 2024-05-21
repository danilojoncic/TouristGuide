package com.example.touristguide.service;

import com.example.touristguide.dto.ArticlePresentationDto;
import com.example.touristguide.dto.CreateArticleDto;
import com.example.touristguide.repository.article.ArticleRepoInterface;

import javax.inject.Inject;
import java.util.List;

public class ArticleService {
    @Inject
    private ArticleRepoInterface articleRepoInterface;

    public void addArticle(CreateArticleDto createArticleDto){
        articleRepoInterface.addArticle(createArticleDto);
    }


    public void deleteArticle(int article_id){
        articleRepoInterface.deleteArticle(article_id);
    }

    public void editArticle(int article_id,CreateArticleDto createArticleDto){
        articleRepoInterface.editArticle(article_id,createArticleDto);
    }

    public ArticlePresentationDto getOneArticle(int article_id){
        return articleRepoInterface.getOneArticle(article_id);
    }

    public List<ArticlePresentationDto> getAllArticles(int page, int pageSize){
        return articleRepoInterface.getAllArticles(page,pageSize);
    }

    public List<ArticlePresentationDto> getPopularArticles(int page, int pageSize){
        return articleRepoInterface.getPopularArticles(page,pageSize);
    }

    public List<ArticlePresentationDto> getLatestArticles(int page, int pageSize){
        return articleRepoInterface.getLatestArticles(page,pageSize);
    }

    public List<ArticlePresentationDto> getArticlesBasedOnCriterium(String criterium){
        return articleRepoInterface.getArticlesBasedOnCriterium(criterium);
    }




}
