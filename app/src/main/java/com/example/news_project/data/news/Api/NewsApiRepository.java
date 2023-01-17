package com.example.news_project.data.news.Api;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Flowable;

public class NewsApiRepository {

    private final String apiKey = "1a935b07aefc499fbeaeeb1f4ae103b0";
    private final NewsApiService newsApiService;

    @Inject
    public NewsApiRepository(NewsApiService newsApiService){
        this.newsApiService = newsApiService;
    }

    public Flowable<NewsEntityQuery> getNews(String target){
        return newsApiService.getNews(target, apiKey);
    }
}
