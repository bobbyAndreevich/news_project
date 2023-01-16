package com.example.news_project.data.news.Api;

import com.example.news_project.data.news.NewsEntity;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class NewsEntityQuery {

    @SerializedName("articles")
    public ArrayList<NewsEntity> news;

}
