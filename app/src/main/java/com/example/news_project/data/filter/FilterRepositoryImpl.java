package com.example.news_project.data.filter;

import com.example.news_project.data.NewsDatabase;
import com.example.news_project.data.mapper.DataFilterToDomainMapper;
import com.example.news_project.data.mapper.DomainFilterToDataMapper;
import com.example.news_project.data.news.NewsLazyCacheManager;
import com.example.news_project.domain.IFilterRepository;
import com.example.news_project.domain.enities.Filter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Flowable;

public class FilterRepositoryImpl implements IFilterRepository {

    private final NewsLazyCacheManager newsLazyCacheManager;
    private final NewsDatabase database;
    private final DomainFilterToDataMapper toDataMapper;
    private final DataFilterToDomainMapper toDomainMapper;
    private final Flowable<List<FilterEntity>> filters;

    @Inject
    public FilterRepositoryImpl(
            NewsDatabase database,
            DomainFilterToDataMapper toDataMapper,
            DataFilterToDomainMapper toDomainMapper,
            NewsLazyCacheManager newsLazyCacheManager){
        this.toDomainMapper = toDomainMapper;
        this.toDataMapper = toDataMapper;
        this.database = database;
        this.newsLazyCacheManager = newsLazyCacheManager;
        filters = database.dataBaseDao().getFilters();
    }

    @Override
    public void addFilter(Filter filter) {
        FilterEntity filterEntity = toDataMapper.Map(filter);
        newsLazyCacheManager.addDataByFilter(filterEntity);
        database.dataBaseDao().insertFiler(filterEntity);
    }

    @Override
    public void deleteFilter(Filter filter) {
        FilterEntity filterEntity = toDataMapper.Map(filter);
        newsLazyCacheManager.deleteDataByFilter(filterEntity);
        database.dataBaseDao().deleteFilter(filterEntity);
    }

    @Override
    public void updateFilter(Filter newFilter, Filter oldFilter) {
        FilterEntity newFilterEntity = toDataMapper.Map(newFilter);
        FilterEntity oldFilterEntity = toDataMapper.Map(oldFilter);
        newsLazyCacheManager.updateDataByFilter(oldFilterEntity, newFilterEntity);
        database.dataBaseDao().updateFilter(newFilterEntity);
    }

    @Override
    public Flowable<List<Filter>> getFilters() {
        return filters.map(toDomainMapper::Map);
    }
}
