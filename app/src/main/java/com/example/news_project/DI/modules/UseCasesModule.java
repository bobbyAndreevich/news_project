package com.example.news_project.DI.modules;

import com.example.news_project.data.filter.FilterRepositoryImpl;
import com.example.news_project.data.news.NewsRepositoryImpl;
import com.example.news_project.domain.use_cases.filter.GetFiltersUseCase;
import com.example.news_project.domain.use_cases.news.GetNewsUseCase;

import dagger.Module;
import dagger.Provides;

@Module
public class UseCasesModule {

    @Provides
    GetNewsUseCase provideGetNewsUseCase(NewsRepositoryImpl repository){
        return new GetNewsUseCase(repository);
    }

    @Provides
    GetFiltersUseCase provideGetFiltersUseCase(FilterRepositoryImpl repository){
        return new GetFiltersUseCase(repository);
    }
}
