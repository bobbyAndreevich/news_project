package com.example.news_project.data.news.Api;

import android.util.Log;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;

public class NewsApiRepository {

    private final NewsApiService newsApiService;

    @Inject
    public NewsApiRepository(NewsApiService newsApiService){
        this.newsApiService = newsApiService;
    }

    public Single<NewsEntityQuery> getNews(String target){
        String apiKey = "69e09ea273064618807a0599214db2fc";
        return newsApiService.getNews(target, apiKey, "ru", "publishedAt");
    }
}
