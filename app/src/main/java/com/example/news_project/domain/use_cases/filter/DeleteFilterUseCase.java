package com.example.news_project.domain.use_cases.filter;

import com.example.news_project.domain.IFilterRepository;
import com.example.news_project.domain.enities.Filter;

import javax.inject.Inject;

public class DeleteFilterUseCase {

    private final IFilterRepository repository;

    @Inject
    public DeleteFilterUseCase(IFilterRepository repository){
        this.repository = repository;
    }

    public void execute(Filter filter){
        repository.deleteFilter(filter);
    }
}
