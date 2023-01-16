package com.example.news_project.data.news.Api;

import java.util.Map;

import io.reactivex.rxjava3.core.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.QueryMap;

public interface NewsApiService {

    @GET("/v2/everything")
    Flowable<NewsEntityQuery> getNews(@Header("q") String target, @Header("apiKey") String apiKey);
}
