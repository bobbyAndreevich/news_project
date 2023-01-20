package com.example.news_project.ui.news.newsAdapter.entities;

import com.example.news_project.domain.enities.News;

public class NewsWrapper implements NewsListDelegate {

    public News value;

    public NewsWrapper(News news){
        value = news;
    }

}
