package com.example.touristguide.repository.article;

import com.example.touristguide.dto.CreateArticleDto;

public interface ArticleRepoInterface {
    void addArticle(CreateArticleDto createArticleDto);
}
