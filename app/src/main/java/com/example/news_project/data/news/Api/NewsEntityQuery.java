package com.example.news_project.data.news.Api;

import com.example.news_project.data.news.NewsEntity;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class NewsEntityQuery {

    @SerializedName("articles")
    public List<NewsEntity> news;
}
