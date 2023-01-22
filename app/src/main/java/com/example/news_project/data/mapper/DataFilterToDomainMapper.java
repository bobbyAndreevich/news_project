package com.example.news_project.data.mapper;

import com.example.news_project.data.filter.FilterEntity;
import com.example.news_project.domain.enities.Filter;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

public class DataFilterToDomainMapper implements IMapper<List<FilterEntity>, List<Filter>> {

    @Inject
    public DataFilterToDomainMapper() {}

    private Filter singleMap(FilterEntity value) {
        Filter filter = new Filter();
        filter.name = value.name;
        filter.description = value.description;
        filter.id = value.id;
        return filter;
    }

    @Override
    public List<Filter> Map(List<FilterEntity> value) {
        return value.stream().map(this::singleMap).collect(Collectors.toList());
    }
}
