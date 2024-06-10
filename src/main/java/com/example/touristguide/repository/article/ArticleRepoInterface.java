package com.example.touristguide.repository.article;

import com.example.touristguide.dto.ArticlePresentationDto;
import com.example.touristguide.dto.CreateArticleDto;

import java.util.List;

public interface ArticleRepoInterface {
    void addArticle(CreateArticleDto createArticleDto);

    void editArticle(int article_id,CreateArticleDto createArticleDto);

    void deleteArticle(int article_id);

    ArticlePresentationDto getOneArticle(int article_id);

    List<ArticlePresentationDto> getAllArticles(int page, int pageSize);

    List<ArticlePresentationDto> getPopularArticles(int page, int pageSize);

    List<ArticlePresentationDto> getLatestArticles(int page, int pageSize);

    //bez page i pageSize za ovo je namjerno
    List<ArticlePresentationDto> getArticlesBasedOnCriterium(String criterium,int page,int pageSize);

}
