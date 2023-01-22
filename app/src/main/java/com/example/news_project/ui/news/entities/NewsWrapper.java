package com.example.news_project.ui.news.entities;

import com.example.news_project.domain.enities.News;

public class NewsWrapper extends NewsListDelegate {

    public News value;

    public NewsWrapper(News news){
        value = news;
    }

}
