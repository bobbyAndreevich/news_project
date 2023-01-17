package com.example.news_project.data.mapper;

import com.example.news_project.data.news.NewsEntity;
import com.example.news_project.domain.enities.News;

import javax.inject.Inject;

public class DomainNewsToDataMapper implements IMapper<News, NewsEntity> {

    @Inject
    public DomainNewsToDataMapper() {
    }

    @Override
    public NewsEntity Map(News value) {
        NewsEntity newsEntity = new NewsEntity();
        newsEntity.publishedAt = value.publishedDate;
        newsEntity.author = value.author;
        newsEntity.newsUrl = value.newsUrl;
        newsEntity.filter = value.filter;
        newsEntity.description = value.description;
        newsEntity.title = value.title;
        newsEntity.imageUrl = value.imageUrl;
        return newsEntity;
    }
}
