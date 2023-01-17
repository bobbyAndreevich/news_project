package com.example.news_project.data.mapper;

import com.example.news_project.data.filter.FilterEntity;
import com.example.news_project.domain.enities.Filter;

import javax.inject.Inject;

public class DomainFilterToDataMapper implements IMapper<Filter, FilterEntity> {

    @Inject
    public DomainFilterToDataMapper() {
    }

    @Override
    public FilterEntity Map(Filter value) {
        FilterEntity filterEntity = new FilterEntity();
        filterEntity.description = value.description;
        filterEntity.id = value.id;
        filterEntity.name = value.name;
        return filterEntity;
    }

}
