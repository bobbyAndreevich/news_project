package com.example.news_project.data.news;

import com.example.news_project.data.NewsDatabase;
import com.example.news_project.data.filter.FilterEntity;
import com.example.news_project.data.news.Api.NewsApiRepository;
import com.example.news_project.data.news.Api.NewsEntityQuery;
import com.example.news_project.domain.enities.Filter;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Single;

public class NewsLazyCacheManager {

    private final NewsDatabase database;
    private final NewsApiRepository apiRepository;

    @Inject
    public NewsLazyCacheManager(NewsDatabase database,
                                NewsApiRepository apiRepository){
        this.apiRepository = apiRepository;
        this.database = database;
    }

    public void updateDataByFilter(FilterEntity oldFilter, FilterEntity newFilter){
        if(!oldFilter.name.equals(newFilter.name)){
            Single<NewsEntityQuery> newsQuery = apiRepository.getNews(newFilter.name);
            newsQuery
                    .doOnSuccess(newsEntityQuery
                            -> newsEntityQuery
                            .news
                            .forEach(newsEntity
                                    -> validNewsInsert(newsEntity, newFilter.name))).subscribe();
            database.dataBaseDao().deleteNewsByFilter(oldFilter.name);
        }
    }

    public void addDataByFilter(FilterEntity filter){
        Single<NewsEntityQuery> query = apiRepository.getNews(filter.name);
        query
                .doOnSuccess(newsEntityQuery
                        -> newsEntityQuery
                        .news
                        .forEach(newsEntity
                                -> validNewsInsert(newsEntity, filter.name))).subscribe();
    }

    public void deleteDataByFilter(FilterEntity filter){
        database.dataBaseDao().deleteNewsByFilter(filter.name);
    }

    private void validNewsInsert(NewsEntity news, String filter){
        news.filter = filter;
        database.dataBaseDao().insertNews(news);
    }

}
