package com.example.news_project.data.filter;

import com.example.news_project.data.NewsDatabase;
import com.example.news_project.data.mapper.DataFilterToDomainMapper;
import com.example.news_project.data.mapper.DomainFilterToDataMapper;
import com.example.news_project.domain.IFilterRepository;
import com.example.news_project.domain.enities.Filter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Flowable;

public class FilterRepositoryImpl implements IFilterRepository {

    private NewsDatabase database;
    private final DomainFilterToDataMapper toDataMapper;
    private final DataFilterToDomainMapper toDomainMapper;
    private final Flowable<List<FilterEntity>> filters;

    @Inject
    public FilterRepositoryImpl(
            NewsDatabase database,
            DomainFilterToDataMapper toDataMapper,
            DataFilterToDomainMapper toDomainMapper){
        this.toDomainMapper = toDomainMapper;
        this.toDataMapper = toDataMapper;
        this.database = database;
        filters = database.dataBaseDao().getFilters();
    }

    @Override
    public void addFilter(Filter filter) {
        FilterEntity filterEntity = toDataMapper.Map(filter);
        database.dataBaseDao().insertFiler(filterEntity);
    }

    @Override
    public void deleteFilter(Filter filter) {
        FilterEntity filterEntity = toDataMapper.Map(filter);
        database.dataBaseDao().deleteNewsByFilter(filterEntity.name);
        database.dataBaseDao().deleteFilter(filterEntity);
    }

    @Override
    public void updateFilter(Filter filter) {
        FilterEntity filterEntity = toDataMapper.Map(filter);
        database.dataBaseDao().updateFilter(filterEntity);
    }

    @Override
    public Flowable<List<Filter>> getFilters() {
        return filters.map(toDomainMapper::Map);
    }
}
