package com.example.news_project.domain;

import com.example.news_project.domain.enities.Filter;
import java.util.List;
import io.reactivex.rxjava3.core.Flowable;

public interface IFilterRepository {

    void addFilter(Filter filter);

    void deleteFilter(Filter filter);

    void updateFilter(Filter newFilter, Filter oldFilter);

    Flowable<List<Filter>> getFilters();
}
