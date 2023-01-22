package com.example.news_project.DI.modules;


import com.example.news_project.data.filter.FilterRepositoryImpl;
import com.example.news_project.domain.IFilterRepository;
import javax.inject.Singleton;
import dagger.Binds;
import dagger.Module;

@Module
public abstract class FilterModule {

    @Binds
    @Singleton
    public abstract IFilterRepository provideFilterRepository(FilterRepositoryImpl impl);
}
