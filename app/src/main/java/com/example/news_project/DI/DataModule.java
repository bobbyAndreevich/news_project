package com.example.news_project.DI;

import com.example.news_project.data.filter.IFilterRepositoryImpl;
import com.example.news_project.data.news.INewsRepositoryImpl;
import com.example.news_project.domain.IFilterRepository;
import com.example.news_project.domain.INewsRepository;

import dagger.Binds;
import dagger.Module;
import dagger.Reusable;

@Module
public abstract class DataModule {

    @Binds
    @Reusable
    abstract INewsRepository getNewsRepository (INewsRepositoryImpl impl);

    @Binds
    @Reusable
    abstract IFilterRepository getFilterRepository(IFilterRepositoryImpl impl);

}
