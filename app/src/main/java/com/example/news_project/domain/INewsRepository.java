package com.example.news_project.domain;

import com.example.news_project.domain.enities.News;
import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

public interface INewsRepository {

    Flowable<List<News>> getNews();
}
