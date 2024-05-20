package com.example.touristguide.repository.article;

import com.example.touristguide.dto.ArticlePresentationDto;
import com.example.touristguide.dto.CreateArticleDto;

import java.util.List;

public interface ArticleRepoInterface {
    void addArticle(CreateArticleDto createArticleDto);

    void editArticle(int article_id,CreateArticleDto createArticleDto);

    void deleteArticle(int article_id);

    ArticlePresentationDto getOneArticle(int article_id);

    List<ArticlePresentationDto> getAllArticles();

    List<ArticlePresentationDto> getPopularArticles();

    List<ArticlePresentationDto> getLatestArticles();

}
