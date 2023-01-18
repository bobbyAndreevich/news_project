package com.example.news_project.data.news;

import com.example.news_project.data.NewsDatabase;
import com.example.news_project.data.filter.FilterEntity;
import com.example.news_project.data.mapper.DataNewsToDomainMapper;
import com.example.news_project.data.news.Api.NewsApiRepository;
import com.example.news_project.domain.INewsRepository;
import com.example.news_project.domain.enities.News;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Flowable;

public class NewsRepositoryImpl implements INewsRepository {

    private final DataNewsToDomainMapper mapper;

    private final Flowable<List<NewsEntity>> news;

    @Inject
    public NewsRepositoryImpl(
            DataNewsToDomainMapper mapper,
            NewsDatabase database) {
        this.mapper = mapper;
        news = database.dataBaseDao().getAllNews();
    }

    @Override
    public Flowable<List<News>> getNews() {
        return news.map(mapper::Map);
    }

}

