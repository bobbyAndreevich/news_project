package com.example.news_project.ui.news.Entities;

public abstract class NewsListItem<T> {

    public T value;

    NewsListItem(T value){
        this.value = value;
    }
}
