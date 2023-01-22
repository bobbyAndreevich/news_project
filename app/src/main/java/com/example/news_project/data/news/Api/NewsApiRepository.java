package com.example.news_project.data.news.Api;

import javax.inject.Inject;
import io.reactivex.rxjava3.core.Single;

public class NewsApiRepository {

    private static final String API_KEY = "69e09ea273064618807a0599214db2fc";
    private static final String LANGUAGE = "ru";
    private static final String SORT_CONDITION = "publishedAt";

    private final NewsApiService newsApiService;

    @Inject
    public NewsApiRepository(NewsApiService newsApiService){
        this.newsApiService = newsApiService;
    }

    public Single<NewsEntityQuery> getNews(String target) {
        return newsApiService.getNews(target, API_KEY, LANGUAGE, SORT_CONDITION);
    }
}
