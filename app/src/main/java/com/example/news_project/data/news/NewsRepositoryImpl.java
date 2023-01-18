package com.example.news_project.data.news;

import android.util.Log;

import com.example.news_project.data.NewsDatabase;
import com.example.news_project.data.filter.FilterEntity;
import com.example.news_project.data.mapper.DataFilterToDomainMapper;
import com.example.news_project.data.mapper.DataNewsToDomainMapper;
import com.example.news_project.data.mapper.IMapper;
import com.example.news_project.data.news.Api.NewsApiRepository;
import com.example.news_project.domain.INewsRepository;
import com.example.news_project.domain.enities.News;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Flowable;

public class NewsRepositoryImpl implements INewsRepository {

    private final NewsDatabase database;
    private final NewsApiRepository apiRepository;
    private final DataNewsToDomainMapper mapper;

    private final Flowable<List<FilterEntity>> filters;
    private final Flowable<List<NewsEntity>> news;

    @Inject
    public NewsRepositoryImpl(
            DataNewsToDomainMapper mapper,
            NewsDatabase database,
            NewsApiRepository apiRepository) {
        this.mapper = mapper;
        this.database = database;
        this.apiRepository = apiRepository;
        filters = database.dataBaseDao().getFilters();
        filters.doOnNext(filterEntities -> filterEntities.forEach(filterEntity -> loadNews(filterEntity.name))).subscribe();
        news = database.dataBaseDao().getNews();
    }

    @Override
    public Flowable<List<News>> getNews() {
        return news.map(mapper::Map);
    }

    private void loadNews(String theme) {
        Flowable<List<NewsEntity>> remoteNews = apiRepository.getNews(theme).map(newsEntityQuery -> newsEntityQuery.news);
        remoteNews.doOnNext(
                newsEntities -> newsEntities
                        .forEach(newsEntity -> validDataBaseInsert(newsEntity, theme)))
                .subscribe();
    }

    private void validDataBaseInsert(NewsEntity news, String filter){
        news.filter = filter;
        database.dataBaseDao().insertNews(news);
    }
}

