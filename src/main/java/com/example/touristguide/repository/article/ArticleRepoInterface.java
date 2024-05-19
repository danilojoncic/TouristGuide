package com.example.touristguide.repository.article;

import com.example.touristguide.dto.ArticlePresentationDto;
import com.example.touristguide.dto.CreateArticleDto;

public interface ArticleRepoInterface {
    void addArticle(CreateArticleDto createArticleDto);

    void deleteArticle(int article_id);

    ArticlePresentationDto getOneArticle(int article_id);
}
