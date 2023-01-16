package com.example.news_project.domain;

import com.example.news_project.domain.enities.Filter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;

public interface IFilterRepository {



    void addFilter(Filter filter);

    void deleteFilter(Filter filter);

    void updateFilter(Filter filter);

    Flowable<List<Filter>> getFilters();

}
