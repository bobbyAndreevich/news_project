package com.example.news_project.ui.news.Entities;

import com.example.news_project.domain.enities.News;

public class NewsListNewsItem extends NewsListItem {

    public News value;

    NewsListNewsItem(News value) {
        super(value);
    }
}
