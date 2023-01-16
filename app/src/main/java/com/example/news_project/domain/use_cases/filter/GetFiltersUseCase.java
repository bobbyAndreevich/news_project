package com.example.news_project.domain.use_cases.filter;

import com.example.news_project.domain.IFilterRepository;
import com.example.news_project.domain.enities.Filter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Flowable;

public class GetFiltersUseCase {

    private final IFilterRepository repository;

    @Inject
    GetFiltersUseCase(IFilterRepository repository){
        this.repository = repository;
    }

    public Flowable<List<Filter>> execute() {
        return repository.getFilters();
    }
}
