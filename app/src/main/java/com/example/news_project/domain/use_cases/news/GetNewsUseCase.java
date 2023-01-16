package com.example.news_project.domain.use_cases.news;

import com.example.news_project.domain.INewsRepository;
import com.example.news_project.domain.enities.News;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Flowable;

public class GetNewsUseCase {

    private final INewsRepository repository;

    @Inject
    GetNewsUseCase(INewsRepository repository){
        this.repository = repository;
    }

    public Flowable<List<News>> execute(){
        return repository.getNews();
    }

}
