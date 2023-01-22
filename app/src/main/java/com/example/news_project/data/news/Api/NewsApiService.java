package com.example.news_project.data.news.Api;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsApiService {

    @GET("v2/everything")
    Single<NewsEntityQuery> getNews(
            @Query("q") String target,
            @Query("apiKey") String apiKey,
            @Query("language") String language,
            @Query("sortBy") String sortBy);
}
