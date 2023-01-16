package com.example.news_project.data.mapper;

public interface IMapper<In, Out> {

    Out Map(In value);

}
