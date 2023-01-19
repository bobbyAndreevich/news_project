package com.example.news_project.data.filter;

import com.example.news_project.data.NewsDatabase;
import com.example.news_project.data.mapper.DataFilterToDomainMapper;
import com.example.news_project.data.mapper.DomainFilterToDataMapper;
import com.example.news_project.data.news.Api.NewsApiRepository;
import com.example.news_project.data.news.Api.NewsEntityQuery;
import com.example.news_project.data.news.NewsEntity;
import com.example.news_project.domain.IFilterRepository;
import com.example.news_project.domain.enities.Filter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;

public class FilterRepositoryImpl implements IFilterRepository {

    private final NewsDatabase database;
    private final NewsApiRepository apiRepository;
    private final DomainFilterToDataMapper toDataMapper;
    private final DataFilterToDomainMapper toDomainMapper;
    private final Flowable<List<FilterEntity>> filters;

    @Inject
    public FilterRepositoryImpl(
            NewsDatabase database,
            DomainFilterToDataMapper toDataMapper,
            DataFilterToDomainMapper toDomainMapper,
            NewsApiRepository apiRepository){
        this.toDomainMapper = toDomainMapper;
        this.toDataMapper = toDataMapper;
        this.database = database;
        this.apiRepository = apiRepository;
        filters = database.dataBaseDao().getFilters();
    }

    @Override
    public void addFilter(Filter filter) {
        Single<NewsEntityQuery> query = apiRepository.getNews(filter.name);
        query.doOnSuccess(newsEntityQuery -> newsEntityQuery.news.forEach(newsEntity -> validNewsInsert(newsEntity, filter.name))).subscribe();
        FilterEntity filterEntity = toDataMapper.Map(filter);
        database.dataBaseDao().insertFiler(filterEntity);
    }

    @Override
    public void deleteFilter(Filter filter) {
        database.dataBaseDao().deleteNewsByFilter(filter.name);
        FilterEntity filterEntity = toDataMapper.Map(filter);
        database.dataBaseDao().deleteFilter(filterEntity);
    }

    @Override
    public void updateFilter(Filter newFilter, Filter oldFilter) {
        FilterEntity filterEntity = toDataMapper.Map(newFilter);
        database.dataBaseDao().deleteNewsByFilter(oldFilter.name);
        database.dataBaseDao().updateFilter(filterEntity);
    }

    private void validNewsInsert(NewsEntity news, String filter){
        news.filter = filter;
        database.dataBaseDao().insertNews(news);
    }

    @Override
    public Flowable<List<Filter>> getFilters() {
        return filters.map(toDomainMapper::Map);
    }
}
