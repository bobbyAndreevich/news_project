package com.example.news_project.data.mapper;

import com.example.news_project.data.news.NewsEntity;
import com.example.news_project.domain.enities.News;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

public class DataNewsToDomainMapper implements IMapper<List<NewsEntity>, List<News>> {

    private static final int START_DATE_INDEX = 0;
    private static final int END_DATE_INDEX = 10;

    @Inject
    public DataNewsToDomainMapper() {}

    private News singleMap(NewsEntity value) {
        News news = new News();
        news.newsUrl = value.newsUrl;
        news.title = value.title;
        news.filter = value.filter;
        news.author = value.author;
        news.description = value.description;
        news.publishedDate = value.publishedAt.substring(START_DATE_INDEX, END_DATE_INDEX);
        news.imageUrl = value.imageUrl;
        return news;
    }


    @Override
    public List<News> Map(List<NewsEntity> value) {
        return value.stream().map(this::singleMap).collect(Collectors.toList());
    }
}
