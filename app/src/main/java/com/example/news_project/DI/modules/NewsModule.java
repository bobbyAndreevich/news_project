package com.example.news_project.DI.modules;


import com.example.news_project.data.NewsDatabase;
import com.example.news_project.data.mapper.DataNewsToDomainMapper;
import com.example.news_project.data.mapper.IMapper;
import com.example.news_project.data.news.Api.NewsApiRepository;
import com.example.news_project.data.news.Api.NewsApiService;
import com.example.news_project.data.news.NewsRepositoryImpl;
import com.example.news_project.domain.INewsRepository;
import com.example.news_project.domain.use_cases.news.GetNewsUseCase;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.Reusable;

@Module
public abstract class NewsModule {

    @Binds
    @Reusable
    public abstract INewsRepository provideNewsRepository(NewsRepositoryImpl impl);

}
