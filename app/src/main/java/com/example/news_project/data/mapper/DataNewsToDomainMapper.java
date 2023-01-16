package com.example.news_project.data.mapper;

import com.example.news_project.data.news.NewsEntity;
import com.example.news_project.domain.enities.News;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DataNewsToDomainMapper implements IMapper<List<NewsEntity>, List<News>>{


    private News singleMap(NewsEntity value) {
        News news = new News();
        news.newsUrl = value.newsUrl;
        news.title = value.title;
        news.filter = value.filter;
        news.author = value.author;
        news.description = value.description;
        news.publishedDate = value.publishedAt.substring(0, 9);
        return news;
    }


    @Override
    public List<News> Map(List<NewsEntity> value) {
        return value.stream().map(this::singleMap).collect(Collectors.toList());
    }
}