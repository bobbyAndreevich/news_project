package com.example.news_project.data.news.Api;

import com.example.news_project.data.filter.FilterEntity;

import java.util.List;
import java.util.Map;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface NewsApiService {

    @GET("v2/everything")
    Single<NewsEntityQuery> getNews(
            @Query("q") String target,
            @Query("apiKey") String apiKey,
            @Query("language") String language,
            @Query("sortBy") String sortBy);
}
